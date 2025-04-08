package com.example.diceroller

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.diceroller.ui.theme.DiceRollerTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DiceRollerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    DiceRollerApp()
                }
            }
        }
    }
}

@Composable
fun DiceWithButtonAndImage(modifier: Modifier = Modifier) {
    var result by remember { androidx.compose.runtime.mutableIntStateOf(1) }

    val diceResource = when (result) {
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        else -> R.drawable.dice_6
    }

    val congratsResource = when (result) {
        6 -> R.drawable.con
        else -> R.drawable.dice
    }

    Column(
        modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = "Roll a 6 and Win!!"
        )
        Image(
            painter = painterResource(congratsResource),
            contentDescription = "Congrats",
            Modifier.height(100.dp)
        )
        Spacer(modifier = Modifier.height(50.dp))
        Image(
            painter = painterResource(diceResource),
            contentDescription = result.toString()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                result = Random.nextInt(1,7)
            }
        ) {
            Text(
                stringResource(R.string.roll),
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DiceRollerApp() {
    DiceWithButtonAndImage(
        modifier = Modifier
            .wrapContentSize(Alignment.Center)
    )
}