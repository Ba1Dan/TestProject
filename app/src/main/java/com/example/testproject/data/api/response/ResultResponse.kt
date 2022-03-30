package com.example.testproject.data.api.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class ResultResponse(
    @SerialName("results")
    val results: List<RecipeResponse>
)