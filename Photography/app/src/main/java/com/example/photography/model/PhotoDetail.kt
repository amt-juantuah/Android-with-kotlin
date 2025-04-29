package com.example.photography.model


import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class PhotoDetail (
    @StringRes val nameResourceId: Int,
    val countResourceId: Int,
    @DrawableRes val imageResourceId: Int,
)