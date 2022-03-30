package com.example.testproject.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.testproject.data.db.entity.RecipeEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface RecipesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveRecipes(recipes: List<RecipeEntity>): Completable

    @Query("SELECT * FROM recipes_table")
    fun getRecipes(): Single<List<RecipeEntity>>
}