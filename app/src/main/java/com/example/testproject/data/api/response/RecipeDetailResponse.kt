package com.example.testproject.data.api.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class RecipeDetailResponse(
    @SerialName("id")
    val id: Int,
    @SerialName("image")
    val image: String,
    @SerialName("title")
    val title: String,
    @SerialName("summary")
    val summary: String? = null,
    @SerialName("readyInMinutes")
    val readyInMinutes: Int? = null
)