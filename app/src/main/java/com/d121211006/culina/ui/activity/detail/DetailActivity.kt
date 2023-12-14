package com.d121211006.culina.ui.activities.detail

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.d121211006.culina.data.models.Recipe
import com.d121211006.culina.ui.theme.CulinaTheme

class DetailActivity : ComponentActivity() {

    private var selectedRecipe: Recipe? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        selectedRecipe = intent.getParcelableExtra("RECIPE")
        setContent {
            CulinaTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DetailScreen(selectedRecipe)
                }
            }
        }
    }

    @Composable
    private fun DetailScreen(recipe: Recipe?) {
        LazyColumn {
            item {
                recipe?.let {
                    AsyncImage(
                        model = ImageRequest.Builder(context = LocalContext.current)
                            .data(it.image)
                            .crossfade(true)
                            .build(),
                        contentDescription = "Recipe Image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Text(text = it.title.toString())
                } ?: run {
                    Text(text = "Recipe not found")
                }
            }
        }
    }
}
