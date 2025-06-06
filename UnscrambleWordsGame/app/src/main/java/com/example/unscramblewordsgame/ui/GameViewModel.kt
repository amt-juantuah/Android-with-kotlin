package com.example.unscramblewordsgame.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.unscramblewordsgame.data.MAX_GAME_COUNT
import com.example.unscramblewordsgame.data.SCORE_INCREMENT
import com.example.unscramblewordsgame.data.WordLists
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class GameViewModel : ViewModel() {

    // ui state
    private val _uiState = MutableStateFlow(GameUiState())
    val uiState: StateFlow<GameUiState> = _uiState.asStateFlow()

    // random word to show
    private var usedWords: MutableSet<String> = mutableSetOf()

    // current word
    private lateinit var currentWord: String

    // user guess word
    var userGuess by mutableStateOf("")
        private set

    // increase game score in state
    private fun increaseGameScore() {
        _uiState.update { currentState ->
            currentState.copy(
                score = currentState.score.plus(SCORE_INCREMENT)
            )
        }
    }

    // increase game word count in state
    private fun increaseGameCount() {
        _uiState.update { currentState ->
            currentState.copy(
                gameCount = currentState.gameCount.inc()
            )
        }
    }

    // scramble a new word in state
    private fun newGameWord() {
        _uiState.update { currentState ->
            currentState.copy(
                currentScrambledWord = pickRandomWordAndShuffle(),
            )
        }
    }

    // scramble a new word in state
    private fun userGuessedWrong(yes: Boolean) {
        _uiState.update { currentState ->
            currentState.copy(
                isGuessedWordWrong = yes
            )
        }
    }

    // set game over to true
    private fun setGameOver() {
        updateUserGuess("")
        _uiState.update { currentState ->
            currentState.copy(
                // set game over as true
                gameOver = true,

                // don't check wrong guess
                isGuessedWordWrong = false,

                // no new word to be guessed again
                currentScrambledWord = ""
            )
        }
    }

    // check if user guess is same as current word
    fun compareUserGuessAndCurrentWord() {
        // last game guard
        if (usedWords.size == MAX_GAME_COUNT) {

            if (userGuess.equals(currentWord, ignoreCase = true)) {
                increaseGameScore()
            }
            setGameOver()
        } else {
            // user guessed right.
            if (userGuess.equals(currentWord, ignoreCase = true)) {
                userGuessedWrong(false)
                increaseGameScore()
            } else {
                // user's guess is wrong, so show an error
                userGuessedWrong(true)
            }

            // clear user guess for the next action
            updateUserGuess("")

            // increase game count
            increaseGameCount()

            // new word to be guessed
            newGameWord()
        }
    }

    // user wants to skip to next word
    fun skipToNextWord() {

        // guard with max-game-rounds
        if (usedWords.size == MAX_GAME_COUNT) {
            setGameOver()
        } else {
            // clear user guess for the next action
            updateUserGuess("")

            // increase game count
            increaseGameCount()

            // new word to be guessed
            newGameWord()
        }
    }

    private fun shuffleWord(word: String): String {
        val shuffledWord = word.toList().shuffled().joinToString("")
        return if (shuffledWord == word) {
            shuffleWord(word)
        } else {
            shuffledWord
        }
    }

    private fun pickRandomWordAndShuffle(): String {
        currentWord = WordLists.allWords.random()

        return if (usedWords.contains(currentWord)) {
            pickRandomWordAndShuffle()
        } else {
            usedWords.add(currentWord)
            shuffleWord(currentWord)
        }
    }

    // update user guess
    fun updateUserGuess(userInput: String) {
        userGuess  = userInput
    }

    // reset the game to start
    fun resetGame() {
        usedWords.clear()
        _uiState.value = GameUiState(
            currentScrambledWord = pickRandomWordAndShuffle(),
            isGuessedWordWrong = false,
            score = 0,
            gameCount = 0
        )
    }

    init {
        resetGame()
    }
}