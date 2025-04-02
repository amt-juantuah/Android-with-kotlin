package com.example.jetpackcomposetutorial

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackcomposetutorial.ui.theme.JetpackComposeTutorialTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackComposeTutorialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                    ) {
                    Column {
                        JetPackImage()
                        JetPackText(
                            title = stringResource(R.string.jetpack_compose_title),
                            intro = stringResource(R.string.jetpack_intro),
                            details = stringResource(R.string.jetpack_details),
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun JetPackImage() {
    val image = painterResource(R.drawable.bg_compose_background)
    Image(
        painter = image,
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxWidth()
            .height(136.dp)
    )
}

@Composable
fun JetPackText(title: String, intro: String, details: String, modifier: Modifier) {
    Column(
        verticalArrangement = Arrangement.spacedBy(20.dp),
        modifier = modifier
            .padding(
                top = 16.dp,
                bottom = 16.dp,
                start = 16.dp,
                end = 16.dp
            ),
        ) {

        Text(
            text = title,
            fontSize = 24.sp,
            textAlign = TextAlign.Left,
        )
        Text(
            text = intro,
            fontSize = 20.sp,
            textAlign = TextAlign.Justify,
        )
        Text(
            text = details,
            fontSize = 20.sp,
            textAlign = TextAlign.Justify,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun JetPackTextPreview() {
    JetpackComposeTutorialTheme {
        JetPackText(
            title = stringResource(R.string.jetpack_compose_title),
            intro = stringResource(R.string.jetpack_intro),
            details = stringResource(R.string.jetpack_details),
            modifier = Modifier.fillMaxSize()
            )
    }
}

@Preview(showBackground = true)
@Composable
fun JetPackImagePreview() {
    JetpackComposeTutorialTheme {
        JetPackImage()
    }
}