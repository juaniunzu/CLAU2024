package com.juanunzu.clau2024

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.juanunzu.clau2024.components.Accordion
import com.juanunzu.clau2024.components.ItemCard
import com.juanunzu.clau2024.components.showToast
import com.juanunzu.clau2024.ui.theme.CLAU2024Theme

class AccordionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CLAU2024Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AccordionScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
private fun AccordionScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current.applicationContext
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        ItemCard(
            productImage = ImageBitmap.imageResource(id = R.drawable.botas_image),
            productTitle = { Text(text = "Botas de viaje") },
            productSubtitle = { Text(text = "Llegan hoy") },
            onFavoriteClick = { showToast(context, "added to favorites") },
            onCartClick = { showToast(context, "added to cart") },
            onInfoClick = { showToast(context, "clicked in more info") },
        )
        CustomAccordion(
            title = "Specs",
            contentText = "- Suela antideslizante\n- Impermeable\n- Plantilla acolchada\n" +
                    "- Aislamiento térmico\n- Cierre de cordones"
        )
        CustomAccordion(
            title = "Colores disponibles",
            contentText = "Los colores disponibles pueden variar. Recomendamos visitar la" +
                    "sección de consultas para verificar colores disponibles antes de comprar."
        )
        CustomAccordion(
            title = "Más información",
            image = ImageBitmap.imageResource(id = R.drawable.snow),
            contentText = "Las botas de nieve son un elemento esencial para quienes " +
                    "disfrutan de actividades en climas fríos y nevados, como el esquí, " +
                    "el snowboard o simplemente caminar por paisajes invernales."
        )
    }
}

@Composable
private fun CustomAccordion(title: String, image: ImageBitmap? = null, contentText: String) {
    var isExpanded by rememberSaveable { mutableStateOf(false) }

    Accordion(
        title = title,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp),
        isExpanded = isExpanded,
        onClick = { expanded ->
            isExpanded = expanded
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            image?.let {
                Image(
                    bitmap = it,
                    modifier = Modifier.fillMaxWidth(),
                    contentScale = ContentScale.FillWidth,
                    contentDescription = null
                )
                Spacer(modifier = Modifier.height(4.dp))
            }
            Text(
                text = contentText,
                style = CLAU2024Theme.typographyBodyS
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun AccordionScreenPreview(modifier: Modifier = Modifier) {
    CLAU2024Theme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            AccordionScreen(modifier = Modifier.padding(innerPadding))
        }
    }
}