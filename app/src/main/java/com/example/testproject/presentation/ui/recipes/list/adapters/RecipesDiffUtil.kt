package com.example.testproject.presentation.ui.recipes.list.adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.testproject.presentation.model.RecipeItem

class RecipesDiffUtil : DiffUtil.ItemCallback<RecipeItem>() {

    override fun areItemsTheSame(oldItem: RecipeItem, newItem: RecipeItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: RecipeItem, newItem: RecipeItem): Boolean {
        return oldItem == newItem
    }
}