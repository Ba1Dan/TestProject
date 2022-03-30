package com.example.testproject.presentation.ui.recipes.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testproject.domain.entity.ResultData
import com.example.testproject.domain.usecase.GetInformationRecipeUseCase
import com.example.testproject.presentation.model.RecipeDetailItem
import com.example.testproject.presentation.model.State
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class DetailViewModel @Inject constructor(private val getInformationRecipeUseCase: GetInformationRecipeUseCase) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _recipe = MutableLiveData<State<RecipeDetailItem>>()
    val recipe: LiveData<State<RecipeDetailItem>> = _recipe

    fun getCategories(id: Int) {
        getInformationRecipeUseCase.execute(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy { result ->
            when (result) {
                    is ResultData.Success -> {
                        _recipe.value = State.Result(result.data)
                    }
                    is ResultData.Error -> {
                        _recipe.value = State.Error(result.message)
                    }
                }
            }.addTo(compositeDisposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}