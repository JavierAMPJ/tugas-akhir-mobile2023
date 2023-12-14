package com.d121211006.culina.data.repository

import com.d121211006.culina.data.models.Recipe
import com.d121211006.culina.data.response.GetRecipesResponse
import com.d121211006.culina.data.source.remote.ApiService
import kotlinx.serialization.SerializationException
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.io.IOException

class RecipesRepository(private val apiService: ApiService) {

    private val json = Json { ignoreUnknownKeys = true }

    suspend fun getRandomRecipe(apiKey: String): Recipe? {
        return try {
            val response = apiService.getRandomRecipe(apiKey)
            response.results?.firstOrNull() // Assuming results is a list and you want the first recipe
        } catch (e: IOException) {
            // Handle IO exceptions
            null
        } catch (e: SerializationException) {
            // Handle serialization exceptions
            null
        }
    }
}

