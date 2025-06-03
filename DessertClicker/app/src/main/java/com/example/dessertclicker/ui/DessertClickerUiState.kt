package com.example.dessertclicker.ui

import com.example.dessertclicker.data.Datasource
import com.example.dessertclicker.model.Dessert

data class DessertClickerUiState(
    val dessert: Dessert = Datasource.dessertList.first(),
    val dessertsSold: Int = 0,
    val revenue: Int = 0,
)
