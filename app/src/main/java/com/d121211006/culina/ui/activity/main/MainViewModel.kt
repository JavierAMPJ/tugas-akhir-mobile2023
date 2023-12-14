package com.d121211006.culina.ui.activities.main

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.d121211006.culina.MyApplication
import com.d121211006.culina.data.models.Recipe
import com.d121211006.culina.data.repository.RecipesRepository
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

    fun getRandomRecipes() = viewModelScope.launch {
        mainUiState = MainUiState.Loading
        try {
            val result = recipesRepository.getRandomRecipes(number = 10)
            Log.d("MainViewModel", "getRandomRecipes: ${result.size}")
            mainUiState = MainUiState.Success(result)
        } catch (e: IOException) {
            Log.d("MainViewModel", "getRandomRecipes error: ${e.message}")
            mainUiState = MainUiState.Error
        }
    }

    init {
        getRandomRecipes()
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MyApplication)
                val recipeRepository = application.container.recipeRepository
                MainViewModel(recipeRepository)
            }
        }
    }
}

