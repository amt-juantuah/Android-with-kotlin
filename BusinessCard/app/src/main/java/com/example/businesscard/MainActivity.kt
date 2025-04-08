package com.example.businesscard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.businesscard.ui.theme.BusinessCardTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BusinessCardTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(210,232,212,)
                ) {
                    BusinessCard("John Nana Doe", "Senior Android Developer")
                    CardContact("+0 000 0000 000", "@AndroidNana", "john.n.doe@android.com")
                }
            }
        }
    }
}

@Composable
fun BusinessCard(name: String, title: String, modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(bottom = 150.dp)
    ) {
        Box(
            modifier
                .fillMaxWidth(0.35f)
                .padding(
                    top = 24.dp,
                    bottom = 16.dp,
                    start = 20.dp,
                    end = 16.dp
                )
                .background(Color(7,48,66))
        ) {
            val image = painterResource(R.drawable.android_logo)

            Image(
                painter = image,
                contentDescription = "Android Logo",
                Modifier.padding(
                    start = 4.dp,
                    end = 4.dp,
                    top = 12.dp,
                    bottom = 12.dp
                )
            )
        }
        Text(
            text = name,
            modifier = modifier,
            fontSize = 40.sp,
            fontWeight = FontWeight.W300,
            color = Color(12,31,19)
        )
        Text(
            text = title,
            modifier = modifier,
            color = Color(4,110,58),
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )
    }
}


@Composable
fun CardContact(tel: String, social: String, email: String) {
    Column(
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(bottom = 50.dp)
    ) {
        Column {
            val icons = Icons

            Row(modifier = Modifier
                .padding(6.dp),
                horizontalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                Icon(
                    imageVector = icons.Default.Call,
                    contentDescription = "call icon",
                    tint = Color(0,109,59)
                )

                Text(
                    text = tel,
                )
            }
            Row(
                modifier = Modifier.padding(6.dp),
                horizontalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                Icon(
                    imageVector = icons.Default.Share,
                    contentDescription = "share icon",
                    tint = Color(0,109,59)
                )
                Text(
                    text = social,
                )
            }

            Row(
                modifier = Modifier.padding(6.dp),
                horizontalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                Icon(
                    imageVector = icons.Default.Email,
                    contentDescription = "email icon",
                    tint = Color(0,109,59)
                )
                Text(
                    text = email,
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BusinessCardTheme {
        BusinessCard("Android", "Developer")
    }
}