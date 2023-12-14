package com.d121211006.culina.ui.activities.main

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.d121211006.culina.data.models.Recipe
import com.d121211006.culina.ui.activities.detail.DetailActivity
import com.d121211006.culina.ui.theme.CulinaTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CulinaTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text(text = "List Recipes") },
                        )
                    },
                    floatingActionButton = {
                        FloatingActionButton(onClick = {}) {
                            Text(text = "Add")
                        }
                    }
                ) {
                    val mainViewModel: MainViewModel = viewModel(factory = MainViewModel.Factory)
                    ListRecipesScreen(mainViewModel.mainUiState)
                }
            }
        }
    }

    @Composable
    private fun ListRecipesScreen(mainUiState: MainUiState, modifier: Modifier = Modifier) {
        when (mainUiState) {
            is MainUiState.Success -> ListRecipes(mainUiState.recipes)
            is MainUiState.Error -> ErrorText()
            is MainUiState.Loading -> LoadingBar()
        }
    }

    @Composable
    private fun ErrorText() {
        Text(text = "ERROR")
    }

    @Composable
    fun LoadingBar() {
        Text(text = "LOADING...")
    }

    @Composable
    private fun ListRecipes(recipes: List<Recipe>, modifier: Modifier = Modifier) {
        LazyColumn(modifier = modifier) {
            items(recipes) { recipe ->
                RecipeCard(recipe = recipe)
            }
        }
    }

    @Composable
    private fun RecipeCard(recipe: Recipe, modifier: Modifier = Modifier) {
        Card(
            modifier = Modifier
                .clickable {
                    val intent = Intent(this, DetailActivity::class.java)
                    intent.putExtra("RECIPE", recipe)
                    startActivity(intent)
                }
                .padding(16.dp)
        ) {
            Column {
                val painter = rememberImagePainter(
                    data = recipe.image,
                    builder = {
                        crossfade(true)
                        placeholder(android.R.drawable.ic_menu_report_image)
                    }
                )
                Image(
                    painter = painter,
                    contentDescription = "Recipe Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                )
                Text(text = recipe.title ?: "No Title")
            }
        }
    }
}
