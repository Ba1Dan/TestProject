package com.example.testproject.data.api

import com.example.testproject.core.Const.API_KEY
import com.example.testproject.data.api.response.RecipeDetailResponse
import com.example.testproject.data.api.response.ResultResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface FoodRecipesApi {

    @GET("/recipes/complexSearch")
    fun searchRecipes(
        @QueryMap searchQuery: Map<String, String>
    ): Single<ResultResponse>

    @GET("/recipes/{id}/information")
    fun getInformationRecipe(
        @Path("id") id: Int,
        @Query("apiKey") apiKey: String = API_KEY
    ): Single<RecipeDetailResponse>
}