package com.example.unscramblewordsgame.ui.test

import com.example.unscramblewordsgame.data.SCORE_INCREMENT
import com.example.unscramblewordsgame.data.getUnscrambledWord
import com.example.unscramblewordsgame.ui.GameViewModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class GameViewModelTest {
    private val viewModel = GameViewModel()

    // Test game success path
    @Test
    fun gameViewModel_CorrectWordGuessed_ScoreUpdatedAndErrorFlagUnset() {

        var currentGameUiState = viewModel.uiState.value

        val correctPlayerWord = getUnscrambledWord(currentGameUiState.currentScrambledWord)

        viewModel.updateUserGuess(correctPlayerWord)
        viewModel.compareUserGuessAndCurrentWord()


        currentGameUiState = viewModel.uiState.value

        // Assert that compareUserGuessAndCurrentWord() method updates isGuessedWordWrong correctly
        assertFalse(currentGameUiState.isGuessedWordWrong)

        // Assert that score is updated correctly
        assertEquals(SCORE_AFTER_FIRST_CORRECT_ANSWER, currentGameUiState.score)
    }

    // Test game error path
    @Test
    fun gameViewModel_IncorrectWordGuessed_ErrorFlagSet() {

        // An incorrect word guess
        val incorrectWordGuess = "nope"

        viewModel.updateUserGuess(incorrectWordGuess)
        viewModel.compareUserGuessAndCurrentWord()

        // get current GameUiState
        val currentGameUiState = viewModel.uiState.value

        // Assert that user guessed wrong word
        assertTrue(currentGameUiState.isGuessedWordWrong)

        // Assert that score is unchanged
        assertEquals(0, currentGameUiState.score)
    }

    // Test game boundary case
    @Test
    fun gameViewModel_Initialization_FirstWordLoaded() {
        val currentGameUiState = viewModel.uiState.value

        // assert that initial state of wrong guess is false
        assertFalse(currentGameUiState.isGuessedWordWrong)

        // assert that initial state of score is 0
        assertEquals(0, currentGameUiState.score)

        // assert that initial state of gameCount is 0
        assertEquals(0, currentGameUiState.gameCount)

        // assert that initial state of game over is false
        assertFalse(currentGameUiState.gameOver)
    }

    companion object {
        private const val SCORE_AFTER_FIRST_CORRECT_ANSWER = SCORE_INCREMENT
    }
}