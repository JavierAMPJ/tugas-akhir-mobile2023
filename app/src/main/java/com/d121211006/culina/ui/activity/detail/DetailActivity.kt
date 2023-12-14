package com.d121211006.culina.ui.activity.detail

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.d121211006.culina.R
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
                    DetailScreen()
                }
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun DetailScreen() {
        LazyColumn(
            contentPadding = PaddingValues(16.dp)
        ) {
            item {
                DetailHeader()
                Spacer(modifier = Modifier.height(16.dp))
                DetailImage()
                Spacer(modifier = Modifier.height(16.dp))
                DetailInfo()
            }
        }
    }

    @Composable
    private fun DetailHeader() {
        CustomAppBar(
            title = selectedRecipe?.title ?: "",
            onNavigateUp = { onBackPressed() }
        )
    }

    @Composable
    private fun DetailImage() {
        selectedRecipe?.let { recipe ->
            Image(
                painter = rememberImagePainter(data = recipe.image),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(Color.Gray)
            )
        }
    }

    @Composable
    private fun DetailInfo() {
        selectedRecipe?.let { recipe ->
            Card(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                Column {
                    Text(
                        text = recipe.title ?: "Untitled Recipe",
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier.padding(16.dp)
                    )
                    // Add more sections with detailed information about the recipe.
                    // Customize based on your Recipe model.
                }
            }
        }
    }


    @Composable
    fun DetailActivityPreview() {
        CulinaTheme {
            DetailScreen()
        }
    }

    @Composable
    private fun CustomAppBar(title: String, onNavigateUp: () -> Unit) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Using Text instead of IconButton
            Text(
                text = "<--", // You can customize the text as needed
                modifier = Modifier.clickable { onNavigateUp() },
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
            )
        }
    }
}
