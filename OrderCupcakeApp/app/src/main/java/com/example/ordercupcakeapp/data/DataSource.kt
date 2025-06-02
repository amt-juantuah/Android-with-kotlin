package com.example.ordercupcakeapp.data

import com.example.ordercupcakeapp.R

object DataSource {
    val flavours = listOf(
        R.string.vanilla,
        R.string.chocolate,
        R.string.red_velvet,
        R.string.salted_velvet,
        R.string.salted_caramel,
        R.string.coffee
    )

    val quantityOptions = listOf(
        Pair(R.string.one_cupcake, 1),
        Pair(R.string.six_cupcake, 6),
        Pair(R.string.twelve_cupcake, 12),
    )
}