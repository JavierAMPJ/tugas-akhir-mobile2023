package com.d121211006.culina.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SpoonacularResponse(
    @SerialName("results")
    val results: List<Recipe> = emptyList(),
    @SerialName("status")
    val status: String?,
    @SerialName("totalResults")
    val totalResults: Int?
)

