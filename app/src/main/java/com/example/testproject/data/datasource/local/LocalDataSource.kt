package com.example.testproject.data.datasource.local

import com.example.testproject.data.db.dao.RecipesDao
import com.example.testproject.data.db.dao.RecipesDetailDao
import com.example.testproject.data.db.entity.RecipeDetailEntity
import com.example.testproject.data.db.entity.RecipeEntity
import com.example.testproject.domain.entity.Category
import com.example.testproject.domain.entity.Recipe
import com.example.testproject.domain.entity.RecipeDetail
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val recipesDao: RecipesDao,
    private val recipesDetailDao: RecipesDetailDao
) {

    fun getCategories(): Single<List<Category>> {
        return Single.just(
            listOf(
                Category(0, "American"),
                Category(1, "German"),
                Category(2, "Italian"),
                Category(3, "Chinese"),
                Category(4, "Spanish")
            )
        )
    }

    fun saveRecipes(recipes: List<Recipe>): Completable {
        return recipesDao.saveRecipes(recipes.map { item ->
            RecipeEntity(item.id, item.title, item.image, item.summary)
        })
    }

    fun getRecipes(): Single<List<Recipe>> {
        return recipesDao.getRecipes().map { list ->
            list.map { entity ->
                Recipe(entity.id, entity.title, entity.image, entity.summary)
            }
        }
    }


    fun saveRecipe(recipe: RecipeDetail): Completable {
        return recipesDetailDao.saveRecipe(
            RecipeDetailEntity(
                recipe.id,
                recipe.title,
                recipe.image,
                recipe.summary,
                recipe.readyInMinutes
            )
        )
    }

    fun getRecipe(id: Int): Single<List<RecipeDetail>> {
        return recipesDetailDao.getRecipe(id).map { list->
            list.map { entity ->
                RecipeDetail(
                    entity.id,
                    entity.title,
                    entity.image,
                    entity.summary,
                    entity.readyInMinutes
                )
            }
        }
    }
}