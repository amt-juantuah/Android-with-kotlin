package com.example.japanfacts.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Fact(
    @DrawableRes val imageRes: Int,
    @StringRes val titleRes: Int,
    @StringRes val factDetailsRes: Int,
    @StringRes val factDay: Int
)
