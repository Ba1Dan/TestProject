package com.example.testproject.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.testproject.data.db.entity.RecipeEntity.Companion.RECIPE_TABLE

@Entity(tableName = RECIPE_TABLE)
data class RecipeEntity(
    @ColumnInfo(name = "user_id") @PrimaryKey val id: Int,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "image") val image: String,
    @ColumnInfo(name = "summary") val summary: String,
) {

    companion object {
        const val RECIPE_TABLE = "recipes_table"
    }
}