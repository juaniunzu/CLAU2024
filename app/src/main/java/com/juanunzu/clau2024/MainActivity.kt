package com.juanunzu.clau2024

import android.content.Intent
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
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.juanunzu.clau2024.ui.theme.CLAU2024Theme
import kotlin.reflect.KFunction0

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CLAU2024Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen(
                        modifier = Modifier.padding(innerPadding),
                        onComposeExampleClick = ::navigateToItemCardComposeActivity,
                        onViewExampleClick = ::navigateToItemCardViewActivity,
                        onAccordionExampleClick = ::navigateToAccordionActivity
                    )
                }
            }
        }
    }

    private fun navigateToItemCardViewActivity() {
        Intent(this@MainActivity, ItemCardViewActivity::class.java).also {
            startActivity(it)
        }
    }

    private fun navigateToItemCardComposeActivity() {
        Intent(this@MainActivity, ItemCardComposeActivity::class.java).also {
            startActivity(it)
        }
    }

    private fun navigateToAccordionActivity() {
        Intent(this@MainActivity, AccordionActivity::class.java).also {
            startActivity(it)
        }
    }
}

@Composable
private fun MainScreen(
    modifier: Modifier = Modifier,
    onComposeExampleClick: () -> Unit = {},
    onViewExampleClick: () -> Unit = {},
    onAccordionExampleClick: () -> Unit = {}
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = { onComposeExampleClick() }) {
            Text("Go to Compose example")
        }
        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = { onViewExampleClick() }) {
            Text("Go to View example")
        }
        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = { onAccordionExampleClick() }) {
            Text("Go to Accordion example")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview(modifier: Modifier = Modifier) {
    CLAU2024Theme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            MainScreen(
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}