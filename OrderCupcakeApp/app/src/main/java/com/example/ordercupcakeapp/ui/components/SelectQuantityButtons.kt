package com.example.ordercupcakeapp.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.ordercupcakeapp.data.DataSource

@Composable
fun SelectQuantityButtons(
    modifier: Modifier = Modifier,
    chooseOrder: (quantity: Int) -> Unit,
    onNextButtonClick: (quantity: Int) -> Unit = {}
) {
    Column(
        modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        DataSource.quantityOptions.map { item ->
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    chooseOrder(item.second)
                    onNextButtonClick(item.second)
                },
            ) {
                Text(
                    text = stringResource(item.first)
                )
            }
        }
    }
}