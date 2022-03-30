package com.example.testproject.data.repository

import com.example.testproject.data.datasource.local.LocalDataSource
import com.example.testproject.domain.entity.ResultData
import com.example.testproject.data.datasource.remote.RemoteDataSource
import com.example.testproject.domain.entity.Category
import com.example.testproject.domain.entity.Recipe
import com.example.testproject.domain.entity.RecipeDetail
import com.example.testproject.domain.repository.ProductRepository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) : ProductRepository {

    override fun getCategories(): Single<List<Category>> {
        return localDataSource.getCategories()
    }

    override fun searchRecipes(searchQuery: Map<String, String>): Single<ResultData<List<Recipe>>> {
        return remoteDataSource.search(searchQuery)
    }

    override fun saveRecipes(recipes: List<Recipe>): Completable {
        return localDataSource.saveRecipes(recipes)
    }

    override fun getInformationRecipe(id: Int): Single<ResultData<RecipeDetail>> {
        return remoteDataSource.getInformationRecipe(id)
    }

    override fun getRecipes(): Single<List<Recipe>> {
        return localDataSource.getRecipes()
    }

    override fun getRecipes(id: Int): Single<List<RecipeDetail>> {
        return localDataSource.getRecipe(id)
    }

    override fun saveRecipe(recipe: RecipeDetail): Completable {
        return localDataSource.saveRecipe(recipe)
    }
}