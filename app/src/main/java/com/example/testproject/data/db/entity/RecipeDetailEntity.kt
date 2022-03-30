package com.example.testproject.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.testproject.data.db.entity.RecipeDetailEntity.Companion.RECIPE_DETAIL_TABLE


@Entity(tableName = RECIPE_DETAIL_TABLE)
data class RecipeDetailEntity(
    @ColumnInfo(name = "id") @PrimaryKey val id: Int,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "image") val image: String,
    @ColumnInfo(name = "summary") val summary: String,
    @ColumnInfo(name = "ready_in_minutes") val readyInMinutes: Int,
) {

    companion object {
        const val RECIPE_DETAIL_TABLE = "recipes_detail_table"
    }
}