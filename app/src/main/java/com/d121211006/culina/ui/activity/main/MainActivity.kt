package com.d121211006.culina.ui.activity.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.d121211006.culina.data.repository.RecipesRepository
import kotlinx.coroutines.launch
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.d121211006.culina.data.models.Recipe
import com.d121211006.culina.ui.activity.detail.DetailActivity
import com.d121211006.culina.ui.theme.CulinaTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private var recipes by mutableStateOf<List<Recipe>>(emptyList())
    private var isRecipeLoading by mutableStateOf(false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CulinaTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun MainScreen() {
        val coroutineScope = rememberCoroutineScope()

        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = "Recipe List") },
                )
            },
            floatingActionButton = {
                FloatingActionButton(onClick = {
                    // Handle the FAB click event
                }) {
                    Text(text = "Add Recipe")
                }
            }
        ) {
            Column(modifier = Modifier.padding(it)) {
                if (isRecipeLoading) {
                    LoadingBar()
                } else {
                    RecipeList(recipes) { selectedRecipe ->
                        coroutineScope.launch {
                            navigateToRecipeDetail(selectedRecipe)
                        }
                    }
                }
            }
        }
    }

    @Composable
    private fun LoadingBar() {
        Text(text = "Loading Recipes...")
    }

    @Composable
    private fun RecipeList(recipes: List<Recipe>, onRecipeClick: (Recipe) -> Unit) {
        LazyColumn {
            items(recipes) { recipe ->
                RecipeCard(recipe = recipe, onRecipeClick = onRecipeClick)
            }
        }
    }

    @Composable
    private fun RecipeCard(recipe: Recipe, onRecipeClick: (Recipe) -> Unit) {
        Card(
            modifier = Modifier
                .clickable { onRecipeClick(recipe) }
                .padding(8.dp),
        ) {
            Column {
                AsyncImage(
                    model = ImageRequest.Builder(context = LocalContext.current)
                        .data(recipe.image)
                        .crossfade(true)
                        .build(),
                    contentDescription = "Recipe Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxWidth().height(200.dp)
                )
                Text(
                    text = recipe.title ?: "Untitled Recipe",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }

    class YourViewModel(private val recipesRepository: RecipesRepository) : ViewModel() {

        // Properties for holding the state of recipes
        private var recipes by mutableStateOf<List<Recipe>>(emptyList())
        private var isRecipeLoading by mutableStateOf(false)

        // Function to fetch random recipes
        private fun loadRecipes() {
            // Simulate loading recipes (replace this with your actual data fetching logic)
            isRecipeLoading = true

            // Assuming getRandomRecipe returns a single random recipe
            viewModelScope.launch {
                try {
                    val randomRecipe = recipesRepository.getRandomRecipe("479576f9a6aa4b90b8d526641230816a")
                    if (randomRecipe != null) {
                        recipes = listOf(randomRecipe)
                    } else {
                        // Handle the case where the random recipe is null
                    }
                } catch (e: Exception) {
                    // Handle exceptions (network, serialization, etc.)
                    e.printStackTrace()
                } finally {
                    isRecipeLoading = false
                }
            }
        }

        // Call this function from your UI or wherever needed
        fun startLoadingRecipes() {
            if (!isRecipeLoading) {
                loadRecipes()
            }
        }

        // ... other ViewModel code ...
    }

    private suspend fun navigateToRecipeDetail(selectedRecipe: Recipe) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("RECIPE", selectedRecipe)
        startActivity(intent)
    }
}
