// File: com/d121211006/culina/data/source/remote/ApiService.kt
package com.d121211006.culina.data.source.remote

import com.d121211006.culina.data.response.GetRecipesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("recipes/random")
    suspend fun getRandomRecipe(
        @Query("apiKey") apiKey: String
    ): GetRecipesResponse
}

