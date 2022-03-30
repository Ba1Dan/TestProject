package com.example.testproject.data.datasource.remote

import com.example.testproject.data.api.FoodRecipesApi
import com.example.testproject.domain.entity.ResultData
import com.example.testproject.data.api.response.toResult
import com.example.testproject.domain.entity.Recipe
import com.example.testproject.domain.entity.RecipeDetail
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val api: FoodRecipesApi) {


    fun search(searchQuery: Map<String, String>): Single<ResultData<List<Recipe>>> {
        return api.searchRecipes(searchQuery).map { result ->
            result.results.map { response ->
                Recipe(response.id, response.title, response.image, response.summary ?: "")
            }
        }.toResult()
    }

    fun getInformationRecipe(id: Int): Single<ResultData<RecipeDetail>> {
        return api.getInformationRecipe(id).map { result ->
                RecipeDetail(result.id, result.title, result.image, result.summary ?: "", result.readyInMinutes ?: -1)
        }.toResult()
    }
}
