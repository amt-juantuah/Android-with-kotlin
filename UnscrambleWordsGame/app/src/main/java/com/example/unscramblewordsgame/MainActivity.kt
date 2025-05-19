package com.example.unscramblewordsgame

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.unscramblewordsgame.ui.GameViewModel
import com.example.unscramblewordsgame.ui.theme.UnscrambleWordsGameTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UnscrambleWordsGameTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    ScrambleApp()
                }
            }
        }
    }
}

@Composable
fun ScrambleApp(
    modifier: Modifier = Modifier,
    gameViewModel: GameViewModel = viewModel()
    ) {

    val gameUiState by gameViewModel.uiState.collectAsState()

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.padding(20.dp)
    ) {
        Text(
            text = stringResource(R.string.unscramble),
            style = MaterialTheme.typography.titleLarge
        )

        GameLayout(
            currentScrambledWord = gameUiState.currentScrambledWord,
            userGuess = gameViewModel.userGuess,
            userInputChange = { gameViewModel.updateUserGuess(it) },
            checkUserGuess = { gameViewModel.compareUserGuessAndCurrentWord() },
            skipToNext = { gameViewModel.skipToNextWord() },
            isGuessWrong = gameUiState.isGuessedWordWrong,
            guessedWords = gameUiState.gameCount,
            score = gameUiState.score,
            gameOver = gameUiState.gameOver,
            restartGame = { gameViewModel.resetGame()}
        )
            
    }


}

@Preview(showBackground = true)
@Composable
fun ScrambleAppPreview() {
    UnscrambleWordsGameTheme {
        ScrambleApp()
    }
}