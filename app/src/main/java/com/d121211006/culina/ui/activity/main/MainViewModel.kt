package com.d121211006.culina.ui.activity.main

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.d121211006.culina.data.repository.RecipesRepository
import com.d121211006.culina.data.models.Recipe
import com.d121211006.culina.MyApplication
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface MainUiState {
    data class Success(val recipes: List<Recipe>) : MainUiState
    object Error : MainUiState
    object Loading : MainUiState
}

class MainViewModel(private val recipesRepository: RecipesRepository) : ViewModel() {

    var mainUiState: MainUiState by mutableStateOf(MainUiState.Loading)
        private set

    fun getRandomRecipe() = viewModelScope.launch {
        mainUiState = MainUiState.Loading
        try {
            val result = recipesRepository.getRandomRecipe("your_api_key_here")
            if (result != null) {
                Log.d("MainViewModel", "getRandomRecipe: ${result.title}")
                mainUiState = MainUiState.Success(listOf(result))
            } else {
                Log.d("MainViewModel", "getRandomRecipe error: Recipe is null")
                mainUiState = MainUiState.Error
            }
        } catch (e: IOException) {
            Log.d("MainViewModel", "getRandomRecipe error: ${e.message}")
            mainUiState = MainUiState.Error
        }
    }

    init {
        getRandomRecipe()
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MyApplication)
                val recipesRepository = application.container.recipesRepository
                MainViewModel(recipesRepository)
            }
        }
    }
}

