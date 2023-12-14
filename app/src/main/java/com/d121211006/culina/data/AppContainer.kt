package com.d121211006.culina.data

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.d121211006.culina.data.repository.RecipesRepository
import com.d121211006.culina.data.source.remote.ApiService
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val recipesRepository: RecipesRepository
}

class DefaultAppContainer: AppContainer {

    private val BASE_URL = "https://spoonacular.com"

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(BASE_URL)
        .build()

    private val retrofitService : ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }

    override val recipesRepository: RecipesRepository
        get() = RecipesRepository(retrofitService)

}