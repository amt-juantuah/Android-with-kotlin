package com.example.photography

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.photography.data.DataSource
import com.example.photography.model.PhotoDetail
import com.example.photography.ui.theme.PhotographyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PhotographyTheme {
                PhotoTileApp()
            }
        }
    }
}

@Composable
fun PhotoTileApp() {
    val layoutDirection = LocalLayoutDirection.current

    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(
                start = WindowInsets.safeDrawing.asPaddingValues().calculateStartPadding(layoutDirection),
                end = WindowInsets.safeDrawing.asPaddingValues().calculateEndPadding(layoutDirection)
            )
    ) {
        PhotoTileGrid(DataSource.topics)
    }
}

@Composable
fun PhotoTileGrid(
    photoTileList: List<PhotoDetail>,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.padding(4.dp)
        ) {
        items(photoTileList) {
                item -> PhotoTileCard(item)
        }
    }
}

@Composable
fun PhotoTileCard(photoDetail: PhotoDetail) {
    Card(modifier = Modifier.padding(4.dp)) {
        Row(
            modifier = Modifier
                .height(68.dp)
        ) {
            Image(
                painter = painterResource(photoDetail.imageResourceId),
                contentDescription = stringResource((photoDetail.nameResourceId)),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(68.dp)
                    .width(68.dp)
//                    .aspectRatio(1f)
            )
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start,
//                modifier = Modifier.aspectRatio(2.5f)

            ) {
                Text(
                    text = stringResource(photoDetail.nameResourceId),
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Light,
                    modifier = Modifier
                        .padding(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 8.dp)
                )
                Row(
                    horizontalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(R.drawable.ic_grain),
                        contentDescription = "",
                        colorFilter = ColorFilter.tint(Color.Black),
                        modifier = Modifier
                            .padding(start = 16.dp, end = 8.dp)
                    )
                    Text(
                        text = photoDetail.countResourceId.toString(),
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Light
                    )
                }
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PhotographyTheme {
        PhotoTileGrid(
            photoTileList = DataSource.topics
        )
    }
}