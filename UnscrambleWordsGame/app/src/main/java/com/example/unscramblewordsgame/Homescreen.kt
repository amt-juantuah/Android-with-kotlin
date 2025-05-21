package com.example.unscramblewordsgame

import android.app.Activity
import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.unscramblewordsgame.data.MAX_GAME_COUNT
import com.example.unscramblewordsgame.data.WINNING_MARK


@Composable
fun GameLayout(
    modifier: Modifier = Modifier,
    currentScrambledWord: String,
    userGuess: String,
    userInputChange: (input: String) -> Unit,
    checkUserGuess: () -> Unit,
    skipToNext: () -> Unit,
    isGuessWrong: Boolean,
    guessedWords: Int,
    score: Int,
    gameOver: Boolean,
    restartGame: () -> Unit
) {

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp, top = 12.dp)
                .clip(shape = MaterialTheme.shapes.medium)
                .background(color = Color.hsl(251F, 0.22F, 0.9F)) //#e3e1ec
                .padding(16.dp)
        ) {
            Text(
                text = stringResource(R.string.game_count, guessedWords+1, MAX_GAME_COUNT),
                textAlign = TextAlign.End,
                color = Color.White,
                modifier = Modifier
                    .padding(all = 0.dp)
                    .clip(shape = MaterialTheme.shapes.small)
                    .background(color = Color.hsl(231F, 0.46F, 0.5F))
                    .padding(start = 8.dp, end = 8.dp, top = 2.dp, bottom = 2.dp)
                    .align(alignment = Alignment.End)

            )

            Text(
                text = currentScrambledWord,
                fontSize = 40.sp,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(16.dp)
            )

            Text(
                text = stringResource(R.string.instruction),
                modifier = Modifier.padding(bottom = 16.dp)
            )

            OutlinedTextField(
                value = userGuess,
                onValueChange = {
                    if (it.length <= currentScrambledWord.length) {
                        userInputChange(it)
                    }
                },
                label = {
                    if (isGuessWrong) {
                        Text(stringResource(R.string.wrong_guess))
                    } else {
                        Text(stringResource(R.string.enter_your_word))
                    }
                },
                enabled = true,
                isError = isGuessWrong,
                singleLine = true,
                maxLines = currentScrambledWord.length,
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.None
                ),
                keyboardActions = KeyboardActions(
                    onDone = {}
                ),
                modifier = Modifier
                    .fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.White,
                    focusedContainerColor = Color.White,
                    unfocusedPlaceholderColor = Color.Blue,
                    unfocusedLabelColor = Color.Blue,
                    focusedLabelColor = Color.Blue
                ),
                shape = MaterialTheme.shapes.medium
            )
        }
        Button(
            onClick = checkUserGuess,
            enabled = userGuess.isNotEmpty(),
            colors = ButtonColors(
                containerColor = Color.hsl(231F, 0.46F, 0.5F),
                contentColor = Color.White,
                disabledContentColor = Color.White,
                disabledContainerColor = Color.hsl(231F, 0.46F, 0.5F),
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(R.string.submit))
        }

        OutlinedButton(
            onClick = skipToNext,
            colors = ButtonColors(
                containerColor = Color.White,
                contentColor = Color.hsl(231F, 0.46F, 0.5F),
                disabledContentColor = Color.Transparent,
                disabledContainerColor = Color.Transparent
            ),
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(stringResource(R.string.skip), color = Color.hsl(231F, 0.46F, 0.5F))
        }

        GameScoreStatus(score = score)


        if (gameOver) {
            GameDialog(
                score = score,
                playAgain = restartGame
            )
        }
    }
}

@Composable
fun GameScoreStatus(
    modifier: Modifier = Modifier,
    score: Int
) {
    Text(
        text = stringResource(R.string.score, score),
        fontWeight = FontWeight.Bold,
        modifier = modifier
            .padding(top = 16.dp)
            .clip(shape = MaterialTheme.shapes.small)
            .background(color = Color.hsl(251F, 0.22F, 0.9F))
            .padding(start = 8.dp, end = 8.dp, top = 2.dp, bottom = 2.dp)

    )
}


@Composable
fun GameDialog(
    modifier: Modifier = Modifier,
    passedGame: Boolean = false,
    score: Int,
    playAgain: () -> Unit,
) {
    val activity = LocalActivity.current as Activity

    AlertDialog(
        onDismissRequest = playAgain,
        title = {
            if (score == WINNING_MARK) {
                Text(stringResource(R.string.congratulations))
            } else {
                Text(stringResource(R.string.sorry_mate_try_again))
            }
        },
        text = { Text(stringResource(R.string.game_score, score)) },
        dismissButton = {
            TextButton(onClick = { activity.finish()}) {
                Text(stringResource(R.string.exit))
            }
        },
        confirmButton = {
            TextButton(onClick = playAgain) {
                Text(stringResource(R.string.play_again))
            }
        }
    )
}