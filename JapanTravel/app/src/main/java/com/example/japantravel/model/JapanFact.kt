package com.example.japantravel.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class JapanFact(
    @StringRes val dayRes: Int,
    @DrawableRes val imageRes: Int,
    @StringRes val factRes: Int,
    @StringRes val factDetailsRes: Int,
)
