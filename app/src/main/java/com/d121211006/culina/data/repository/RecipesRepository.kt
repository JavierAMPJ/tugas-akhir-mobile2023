package com.d121211006.culina.data.repository

import com.d121211006.culina.data.models.Recipe
import com.d121211006.culina.data.source.remote.ApiService

class RecipesRepository(private val apiService: ApiService) {

    suspend fun getRandomRecipes(number: Int): List<Recipe> {
        return apiService.getRandomRecipes(number)
    }
}
