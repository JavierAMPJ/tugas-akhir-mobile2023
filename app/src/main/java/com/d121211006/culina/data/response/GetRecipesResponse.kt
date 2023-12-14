// File: com/d121211006/culina/data/response/GetRecipesRepository.kt
package com.d121211006.culina.data.response

import com.d121211006.culina.data.models.Recipe
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetRecipesResponse(
    @SerialName("results")
    val results: List<Recipe>?,
    @SerialName("offset")
    val offset: Int,
    @SerialName("number")
    val number: Int,
    @SerialName("totalResults")
    val totalResults: Int
)
