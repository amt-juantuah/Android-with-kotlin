package com.example.unscramblewordsgame.data

const val SCORE_INCREMENT = 1
const val MAX_GAME_COUNT = 10
const val WINNING_MARK = 7

object WordLists {
    val allWords: Set<String> =
        setOf(
            "at",
            "sea",
            "home",
            "arise",
            "banana",
            "android",
            "birthday",
            "briefcase",
            "motorcycle",
            "cauliflower"
        )
}

/**
 * Maps words to their lengths. Each word in allWords has a unique length. This is required since
 * the words are randomly picked inside GameViewModel and the selection is unpredictable.
 */
private val wordLengthMap: Map<Int, String> = WordLists.allWords.associateBy({ it.length }, { it })

internal fun getUnscrambledWord(scrambledWord: String) = wordLengthMap[scrambledWord.length] ?: ""