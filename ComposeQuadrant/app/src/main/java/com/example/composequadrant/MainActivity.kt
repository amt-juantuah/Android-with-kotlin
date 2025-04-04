package com.example.composequadrant

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composequadrant.ui.theme.ComposeQuadrantTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeQuadrantTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    QuadrantSectionScreen()
                }
            }
        }
    }
}

@Composable
fun QuadrantSectionScreen() {
    Column(
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
            .fillMaxHeight()
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.weight(1f)
        ) {
            BoxSection(
                title = "Text composable",
                description = "Displays text and follows the recommended Material Design guidelines.",
                color = Color(0xFFEADDFF),
                Modifier.weight(0.5f),
//                position = Arrangement.Start,
//                side = Arrangement.Top,
            )
            BoxSection(
                title = "Text composable",
                description = "Displays text and follows the recommended Material Design guidelines.",
                color = Color(0xFFD0BCFF),
                Modifier.weight(0.5f),
//                position = Arrangement.End,
//                side = Arrangement.Top
            )
        }
        Row(
            modifier = Modifier.weight(1f)
        ) {
            BoxSection(
                title = "Text composable",
                description = "Displays text and follows the recommended Material Design guidelines.",
                color = Color(0xFFB69DF8),
                Modifier.weight(0.5f),
//                position = Arrangement.Start,
//                side = Arrangement.Bottom,
            )
            BoxSection(
                title = "Text composable",
                description = "Displays text and follows the recommended Material Design guidelines.",
                color = Color(0xFFF6EDFF),
                Modifier.weight(0.5f),
//                position = Arrangement.End,
//                side = Arrangement.Bottom
            )
        }
    }
}

@Composable
private fun BoxSection(
    title:String,
    description: String,
    color: Color,
//    position: Arrangement.Horizontal,
//    side: Arrangement.Vertical,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
        .background(color)
        .fillMaxHeight(1f)
        .fillMaxWidth(0.5f)
        .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            modifier = Modifier.padding(bottom = 16.dp),
            fontWeight = FontWeight.Bold
        )
        Text(
            text = description,
            textAlign = TextAlign.Justify,
        )
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeQuadrantTheme {
        QuadrantSectionScreen()
    }
}