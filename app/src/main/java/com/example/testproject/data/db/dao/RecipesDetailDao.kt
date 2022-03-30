package com.example.testproject.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.testproject.data.db.entity.RecipeDetailEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface RecipesDetailDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveRecipe(recipes: RecipeDetailEntity): Completable

    @Query("SELECT * FROM recipes_detail_table WHERE id = :id ")
    fun getRecipe(id: Int): Single<List<RecipeDetailEntity>>
}