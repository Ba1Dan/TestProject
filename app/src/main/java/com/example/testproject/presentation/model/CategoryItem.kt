package com.example.testproject.presentation.model

data class CategoryItem(
    val id: Int,
    val title: String,
    var isSelected: Boolean = false
)