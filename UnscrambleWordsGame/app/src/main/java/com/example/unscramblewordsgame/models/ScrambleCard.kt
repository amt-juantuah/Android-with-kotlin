package com.example.unscramblewordsgame.models

import com.example.unscramblewordsgame.data.WordLists

class ScrambleCard(

) {
    private fun wordList(): List<String> {
        return WordLists.generateRandomList()
    }
    var gameComplete = false
        set(value) {field = value}

    private var newList = wordList().shuffled()
    private var id = 0
        set(value) {
            if (value <= newList.count() - 1) {
                field = value
            }
        }

    private var totalAnswered = 0
        set(value) {
            if (value <= newList.count()) {
                field = value
            }
        }

    var totalAnsweredCorrect = 0
        set(value) {
            if (value <= newList.count()) {
                field = value
            }
        }
    var passedGame = totalAnsweredCorrect / newList.count() > 0.7

    var currentWord = newList[id]
    var shuffWord = currentWord.toList().shuffled().joinToString("")


    fun shuffledWord(): String {
//        do {
//            shuffWord = currentWord.toList().shuffled().joinToString("")
//        }while (shuffWord == currentWord)
        return shuffWord
    }

    var totalAnsweredString = "0/${newList.count()}"
        set(value) {field = value}

    private fun nextWord() {
        id++
        totalAnswered++
        currentWord = newList[id]
        shuffWord = currentWord.toList().shuffled().joinToString("")
        totalAnsweredString = "$totalAnswered/${newList.count()}"
        passedGame = totalAnsweredCorrect / newList.count() > 0.7

        if (totalAnswered == newList.count()) {
            gameComplete = true
        }
    }


    fun submit(typedWord: String) {
//        check if word is same as word in memory
        if (typedWord == currentWord) {
            totalAnsweredCorrect++
        }
        nextWord()
    }

    fun skip() {
        nextWord()
    }

    fun reset() {
        newList = wordList().shuffled()
        gameComplete = false
        passedGame = false
        id = 0
        totalAnswered = 0
        totalAnsweredCorrect = 0
    }
}