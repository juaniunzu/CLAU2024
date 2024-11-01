package com.juanunzu.clau2024.components

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.semantics.CustomAccessibilityAction
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.customActions
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.juanunzu.clau2024.R
import com.juanunzu.clau2024.ui.theme.CLAU2024Theme

@Composable
fun ItemCard(
    productImage: ImageBitmap,
    productTitle: String,
    productSubtitle: String,
    onFavoriteClick: () -> Unit,
    onCartClick: () -> Unit,
    onInfoClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .clearAndSetSemantics {
                contentDescription = "$productTitle, $productSubtitle"
                customActions = listOf(
                    CustomAccessibilityAction(
                        label = "add to favorites",
                        action = { onFavoriteClick(); true }),
                    CustomAccessibilityAction(
                        label = "add to cart",
                        action = { onCartClick(); true }),
                    CustomAccessibilityAction(
                        label = "more info",
                        action = { onInfoClick(); true }
                    )
                )
            },
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(width = 1.dp, color = CLAU2024Theme.accentColor),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.elevatedCardElevation()
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(6.dp),
            modifier = Modifier.padding(8.dp)
        ) {
            Image(
                bitmap = productImage,
                contentDescription = null,
                modifier = Modifier
                    .width(88.dp)
                    .height(90.dp)
            )
            Text(text = productTitle, style = CLAU2024Theme.typographyBodyS)
            Text(
                text = productSubtitle,
                color = CLAU2024Theme.textPositiveColor,
                style = CLAU2024Theme.typographyBodyXs
            )
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Image(
                    bitmap = ImageBitmap.imageResource(id = R.drawable.favorite_filled_24),
                    contentDescription = null,
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { onFavoriteClick() },
                    colorFilter = ColorFilter.tint(CLAU2024Theme.accentColor),
                )
                Image(
                    bitmap = ImageBitmap.imageResource(id = R.drawable.cart_24),
                    contentDescription = null,
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { onCartClick() },
                    colorFilter = ColorFilter.tint(CLAU2024Theme.accentColor),
                )
                Image(
                    bitmap = ImageBitmap.imageResource(id = R.drawable.info_24),
                    contentDescription = null,
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { onInfoClick() },
                    colorFilter = ColorFilter.tint(CLAU2024Theme.accentColor),
                )
            }
        }
    }
}

@Composable
fun ItemCard(
    productImage: ImageBitmap,
    productTitle: @Composable () -> Unit,
    productSubtitle: @Composable () -> Unit,
    onFavoriteClick: () -> Unit,
    onCartClick: () -> Unit,
    onInfoClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .semantics(mergeDescendants = true) {
                customActions = listOf(
                    CustomAccessibilityAction(
                        label = "add to favorites",
                        action = { onFavoriteClick(); true }),
                    CustomAccessibilityAction(
                        label = "add to cart",
                        action = { onCartClick(); true }),
                    CustomAccessibilityAction(
                        label = "more info",
                        action = { onInfoClick(); true }
                    )
                )
            },
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(width = 1.dp, color = CLAU2024Theme.accentColor),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.elevatedCardElevation()
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(6.dp),
            modifier = Modifier.padding(8.dp)
        ) {
            Image(
                bitmap = productImage,
                contentDescription = null,
                modifier = Modifier
                    .width(88.dp)
                    .height(90.dp)
            )
            CompositionLocalProvider(
                LocalTextStyle provides CLAU2024Theme.typographyBodyS
            ) {
                productTitle()
            }
            CompositionLocalProvider(
                LocalTextStyle provides CLAU2024Theme.typographyBodyXs,
                LocalContentColor provides CLAU2024Theme.textPositiveColor
            ) {
                productSubtitle()
            }
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Image(
                    bitmap = ImageBitmap.imageResource(id = R.drawable.favorite_filled_24),
                    contentDescription = null,
                    modifier = Modifier
                        .size(24.dp)
                        .clearAndSetSemantics { }
                        .clickable { onFavoriteClick() },
                    colorFilter = ColorFilter.tint(CLAU2024Theme.accentColor),
                )
                Image(
                    bitmap = ImageBitmap.imageResource(id = R.drawable.cart_24),
                    contentDescription = null,
                    modifier = Modifier
                        .size(24.dp)
                        .clearAndSetSemantics { }
                        .clickable { onCartClick() },
                    colorFilter = ColorFilter.tint(CLAU2024Theme.accentColor),
                )
                Image(
                    bitmap = ImageBitmap.imageResource(id = R.drawable.info_24),
                    contentDescription = null,
                    modifier = Modifier
                        .size(24.dp)
                        .clearAndSetSemantics { }
                        .clickable { onInfoClick() },
                    colorFilter = ColorFilter.tint(CLAU2024Theme.accentColor),
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ItemCardPreviews(modifier: Modifier = Modifier) {
    CLAU2024Theme {
        val context = LocalContext.current.applicationContext
        Column(
            modifier = Modifier.fillMaxSize(),
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
}

fun showToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}
