package com.example.artspaceapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.HistoricalChange
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspaceapp.ui.theme.ArtSpaceAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArtSpaceAppTheme {
                Surface(modifier = Modifier
                    .fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArtSpaceApp()
                }
            }
        }
    }
}




@Preview(showBackground = true)
@Composable
fun ArtSpaceApp(modifier: Modifier = Modifier) {
    var art by remember { mutableIntStateOf(0) }


    val artWorks = mapOf(
        0 to mapOf(
            "image" to R.drawable.zlatyu_boyadzhiev_autumn_1941_trivium_art_history_1_1200x0,
            "title" to "Autumn",
            "author" to "Zlatyu Boyadzhiev",
            "year" to " (1941)"
        ),
        1 to mapOf(
            "image" to R.drawable.le_ph_girl_with_a_rose_1941_trivium_art_history_1200x0,
            "title" to "Girl with a rose",
            "author" to "Lê Phổ",
            "year" to " (1941)"
        ),
        2 to mapOf(
            "image" to R.drawable.william_h__johnson_children_1941_trivium_art_history_1_1200x0,
            "title" to "Children",
            "author" to "William H. Johnson",
            "year" to " (1941)"
        ),
        3 to mapOf(
            "image" to R.drawable.norman_lewis_meeting_place_1941_trivium_art_history_1200x0,
            "title" to "Meeting Place",
            "author" to "Norman Lewis",
            "year" to " (1941)"
        )
    )

    fun getElementToShow(index: Int, element: String): String {
        val item = artWorks[index]
        return item?.get(element).toString()
    }

    Column(
        modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(
                start = 30.dp,
                end = 30.dp,
                bottom = 20.dp
            )
        ,
        verticalArrangement = Arrangement.Bottom,
//        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(1f)
                .fillMaxHeight(0.6f)
                .padding(
                    bottom = 20.dp
                )
        ) {
            Image(
                painter = painterResource(getElementToShow(art, "image").toInt()),
                contentDescription = "hello",
                modifier
                    .fillMaxWidth(1f)
                    .fillMaxHeight(0.9f)
                    .background(Color.White)
                    .shadow(
                        elevation = 5.dp,
                        ambientColor = Color.Gray,
                        spotColor = Color.Gray
                    )
                    .padding(
                        start = 30.dp,
                        end = 30.dp,
                    )
            )
        }

        Column(
            modifier
                .fillMaxWidth()
                .background(Color(236, 235, 244))
                .padding(20.dp)
        ) {
            Text(
                text = getElementToShow(art, "title"),
                fontSize = 25.sp,
                fontWeight = FontWeight.Light,
            )

            Row {
                Text(
                    text = getElementToShow(art, "author"),
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = getElementToShow(art, "year"),
                    fontWeight = FontWeight.Light
                )
            }
        }

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = modifier
                .fillMaxWidth()

                .padding(
                    top = 40.dp,
                    bottom = 10.dp
                )
        ) {
            Button(
                onClick = {
                    if (art > 0) {
                        art--
                    } else {
                        art = artWorks.size - 1
                    }
                },
                modifier.width(120.dp)
            ) {
                Text(text = "Previous")
            }

            Button(
                onClick = {
                    if (art < artWorks.size - 1) {
                        art++
                    } else {
                        art = 0
                    }
                },
                modifier.width(120.dp)
            ) {
                Text(text = "Next")
            }
        }
    }
}