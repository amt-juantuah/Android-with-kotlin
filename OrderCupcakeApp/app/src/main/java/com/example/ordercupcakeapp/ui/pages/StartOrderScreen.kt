package com.example.ordercupcakeapp.ui.pages

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ordercupcakeapp.R
import com.example.ordercupcakeapp.ui.components.CupcakeDisplay
import com.example.ordercupcakeapp.ui.components.SelectQuantityButtons

@Composable
fun StartOrderScreen(
    modifier: Modifier = Modifier,
    chooseOrder: (quantity: Int) -> Unit,
    onNextButtonClick: (Int) -> Unit = {},
    onCancelButtonClick: (quantity: Int) -> Unit = {},
) {

    Column(
        modifier
            .padding(
                vertical = 28.dp,
                horizontal = 16.dp
            )
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        CupcakeDisplay()
        SelectQuantityButtons(
            chooseOrder = chooseOrder,
            onNextButtonClick = onNextButtonClick
            )
    }
}



