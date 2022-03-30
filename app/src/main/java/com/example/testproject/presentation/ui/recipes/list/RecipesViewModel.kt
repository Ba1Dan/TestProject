package com.example.testproject.presentation.ui.recipes.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testproject.domain.entity.ResultData
import com.example.testproject.domain.usecase.GetCategoriesUseCase
import com.example.testproject.domain.usecase.SearchRecipesUseCase
import com.example.testproject.presentation.model.CategoryItem
import com.example.testproject.presentation.model.RecipeItem
import com.example.testproject.presentation.model.State
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class RecipesViewModel @Inject constructor(
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val searchRecipesUseCase: SearchRecipesUseCase
) : ViewModel() {

    private var categoriesList = ArrayList<CategoryItem>()
    private val compositeDisposable = CompositeDisposable()

    private val _categories = MutableLiveData<List<CategoryItem>>()
    val categories: LiveData<List<CategoryItem>> = _categories

    private val _recipes = MutableLiveData<State<List<RecipeItem>>>()
    val recipes: LiveData<State<List<RecipeItem>>> = _recipes

    init {
        getCategories()
    }

    fun select(id: Int) {
        val element = categoriesList.find { item -> item.id == id }
        if (element != null) {
            val index = categoriesList.indexOf(element)
            categoriesList[index].isSelected = true
            _categories.value = categoriesList
            search(formatListToString())
        }
    }

    fun unselect(id: Int) {
        val element = categoriesList.find { item -> item.id == id }
        if (element != null) {
            val index = categoriesList.indexOf(element)
            categoriesList[index].isSelected = false
            _categories.value = categoriesList
            search(formatListToString())
        }
    }

    private fun getCategories() {
        getCategoriesUseCase.execute()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy { data ->
                categoriesList = data as ArrayList<CategoryItem>
                select(0)
            }.addTo(compositeDisposable)
    }

    private fun search(query: String) {
        searchRecipesUseCase.execute(query)
            .subscribeOn(Schedulers.io())
            .doOnSubscribe { _recipes.postValue(State.Loading) }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy { result ->
                when (result) {
                    is ResultData.Success -> {
                        _recipes.value = State.Result(result.data)
                    }
                    is ResultData.Error -> {
                        _recipes.value = State.Error(result.message)
                    }
                }
            }.addTo(compositeDisposable)
    }

    private fun formatListToString(): String {
        return categoriesList.filter { it.isSelected }
            .joinToString(SEPARATOR) { it.title.lowercase() }
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    companion object {
        private const val SEPARATOR = ","
    }
}