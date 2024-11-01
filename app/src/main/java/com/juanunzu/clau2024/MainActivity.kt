package com.juanunzu.clau2024

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import com.juanunzu.clau2024.components.ItemCard
import com.juanunzu.clau2024.components.showToast
import com.juanunzu.clau2024.ui.theme.CLAU2024Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CLAU2024Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ItemCardShowcase(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun ItemCardShowcase(modifier: Modifier = Modifier) {
    val context = LocalContext.current.applicationContext
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        ItemCard(
            productImage = ImageBitmap.imageResource(id = R.drawable.botas_image),
            productTitle = "Botas de viaje",
            productSubtitle = "Llegan hoy",
            onFavoriteClick = { showToast(context, "added to favorites") },
            onCartClick = { showToast(context, "added to cart") },
            onInfoClick = { showToast(context, "clicked in more info") },
        )
        Spacer(modifier = Modifier.height(24.dp))
        ItemCard(
            productImage = ImageBitmap.imageResource(id = R.drawable.botas_image),
            productTitle = { Text(text = "Botas de viaje") },
            productSubtitle = { Text(text = "Llegan hoy") },
            onFavoriteClick = { showToast(context, "added to favorites") },
            onCartClick = { showToast(context, "added to cart") },
            onInfoClick = { showToast(context, "clicked in more info") },
        )
    }
}
