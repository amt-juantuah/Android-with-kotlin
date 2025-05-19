package com.example.unscramblewordsgame.data

val SCORE_INCREMENT = 1
val MAX_GAME_COUNT = 10
val WINNING_MARK = 7

object WordLists {
    private val list1 = listOf("jaguar", "cougar", "leopard", "bobcat", "tiger", "ocelot", "panther", "lynx", "lion", "cheetah")
    private val list2 = listOf("leopard", "tiger", "panther", "jaguar", "lion", "ocelot", "bobcat", "cheetah", "lynx", "cougar")
    private val list3 = listOf("leopard", "lion", "tiger", "cougar", "bobcat", "ocelot", "panther", "jaguar", "lynx", "cheetah")
    private val list4 = listOf("bike", "truck", "car", "scooter", "jeep", "bus", "subway", "van", "tram", "train")
    private val list5 = listOf("black", "green", "red", "pink", "blue", "purple", "white", "brown", "yellow", "orange")
    private val list6 = listOf("tulip", "daisy", "iris", "marigold", "peony", "rose", "sunflower", "lavender", "orchid", "lily")
    private val list7 = listOf("parrot", "snake", "hamster", "rabbit", "fish", "frog", "turtle", "cat", "dog", "ferret")
    private val list8 = listOf("dog", "snake", "parrot", "frog", "fish", "hamster", "cat", "turtle", "rabbit", "ferret")
    private val list9 = listOf("turtle", "frog", "snake", "cat", "hamster", "parrot", "dog", "rabbit", "ferret", "fish")
    private val list10 = listOf("sun", "moon", "planet", "galaxy", "nebula", "asteroid", "meteor", "comet", "star", "satellite")

    private val allLists = listOf(
        list1, list2, list3, list4, list5, list6, list7, list8, list9, list10
    )

    val allWords = allLists.flatten()

    fun generateRandomList(): List<String> {
        return allLists.random()
    }
}