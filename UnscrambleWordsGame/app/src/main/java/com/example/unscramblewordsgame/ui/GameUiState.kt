package com.example.unscramblewordsgame.ui

data class GameUiState(
    val currentScrambledWord: String = "",
    val isGuessedWordWrong: Boolean = false,
    val score: Int = 0,
    val gameCount: Int = 0,
    val gameOver: Boolean = false
)
