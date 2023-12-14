package com.d121211006.culina.data.source.remote

import com.d121211006.culina.data.models.Recipe
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("recipes/random")
    suspend fun getRandomRecipes(
        @Query("number") number: Int,
        @Query("apiKey") apiKey: String = "479576f9a6aa4b90b8d526641230816a"
    ): List<Recipe>
}
