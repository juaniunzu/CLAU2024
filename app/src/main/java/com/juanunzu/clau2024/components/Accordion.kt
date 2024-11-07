package com.juanunzu.clau2024.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.juanunzu.clau2024.R
import com.juanunzu.clau2024.ui.theme.CLAU2024Theme

@Composable
fun Accordion(
    title: String,
    modifier: Modifier = Modifier,
    onClick: (Boolean) -> Unit = {},
    isExpanded: Boolean = false,
    content: @Composable () -> Unit
) {
    Column(
        modifier = modifier
    ) {
        val chevronBitmap = if (isExpanded) {
            ImageBitmap.imageResource(id = R.drawable.chevron_down_16)
        } else {
            ImageBitmap.imageResource(id = R.drawable.chevron_right_16)
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(
                    // reemplaza el "activate" en el "double-tap to activate".
                    // de esa manera, cuando está expandido dice "double-tap to collapse"
                    // y "double-tap to expand" cuando está colapsado.
                    onClickLabel = if (isExpanded) "collapse" else "expand"
                ) {
                    onClick(!isExpanded)
                }
                .semantics {
                    // agrega el wording "Collapsed" o "Expanded" según el estado.
                    // con este cambio, las lecturas finales serán:

                    // "Collapsed, {texto pasado en el $title}, double-tap to expand"
                    // "Expanded, {texto pasado en el $title}, double-tap to collapse"

                    // adicionalmente, cuando haya un cambio de estado automáticamente
                    // se anunuciará en voz alta: "Expanded" para cuando se expanda, "Collapsed"
                    // para cuando se contraiga.
                    stateDescription = if (isExpanded) "Collapsed" else "Expanded"
                },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                modifier = Modifier.weight(1f),
                style = CLAU2024Theme.typographyBodyS,
                color = CLAU2024Theme.accentColor
            )
            Image(
                bitmap = chevronBitmap,
                contentDescription = null,
                colorFilter = ColorFilter.tint(CLAU2024Theme.accentColor)
            )
        }
        AnimatedVisibility(visible = isExpanded, modifier = Modifier.fillMaxWidth()) {
            content()
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun AccordionPreview(modifier: Modifier = Modifier) {
    CLAU2024Theme {
        var isExpanded by rememberSaveable {
            mutableStateOf(false)
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(top = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Accordion(
                title = "Más información sobre zapatillas",
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
                    Image(
                        bitmap = ImageBitmap.imageResource(id = R.drawable.running),
                        modifier = Modifier.fillMaxWidth(),
                        contentScale = ContentScale.FillWidth,
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Tenis ideales para competencias, se adaptan perfectamente " +
                                "a la forma del pie, permite circular el aire y estar siempre fresco.",
                        style = CLAU2024Theme.typographyBodyS
                    )
                }
            }
        }
    }
}
