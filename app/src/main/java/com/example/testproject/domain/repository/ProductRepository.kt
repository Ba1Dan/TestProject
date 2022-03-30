package com.example.testproject.domain.repository

import com.example.testproject.domain.entity.Category
import com.example.testproject.domain.entity.Recipe
import com.example.testproject.domain.entity.RecipeDetail
import com.example.testproject.domain.entity.ResultData
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface ProductRepository {

    fun getCategories(): Single<List<Category>>

    fun searchRecipes(searchQuery: Map<String, String>): Single<ResultData<List<Recipe>>>

    fun getRecipes(): Single<List<Recipe>>

    fun getInformationRecipe(id:Int): Single<ResultData<RecipeDetail>>

    fun saveRecipes(recipes: List<Recipe>): Completable

    fun getRecipes(id: Int): Single<List<RecipeDetail>>

    fun saveRecipe(recipe: RecipeDetail): Completable
}