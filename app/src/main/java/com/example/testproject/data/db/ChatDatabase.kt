package com.example.testproject.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.testproject.data.db.dao.RecipesDao
import com.example.testproject.data.db.dao.RecipesDetailDao
import com.example.testproject.data.db.entity.RecipeDetailEntity
import com.example.testproject.data.db.entity.RecipeEntity


@Database(entities = [RecipeEntity::class, RecipeDetailEntity::class], version = 1, exportSchema = false)
abstract class ChatDatabase : RoomDatabase() {

    abstract fun recipesDao(): RecipesDao
    abstract fun recipesDetailDao(): RecipesDetailDao

    companion object {
        const val DATABASE_NAME = "chat_database"
    }
}