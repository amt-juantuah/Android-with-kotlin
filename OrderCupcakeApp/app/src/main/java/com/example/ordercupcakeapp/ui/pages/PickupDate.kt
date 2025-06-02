package com.example.ordercupcakeapp.ui.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.ordercupcakeapp.R
import com.example.ordercupcakeapp.ui.components.NavButtons

@Composable
fun PickupDate(
    modifier: Modifier = Modifier,
    date: String,
    subTotal: String,
    setDate: (date: String) -> Unit,
    dates: List<String>,
    onNextButtonClick: () -> Unit = {},
    onCancelButtonClick: () -> Unit = {}
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
        Column {

            dates.map { dateOption ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(
                        selected = date == dateOption,
                        onClick = { setDate(dateOption) }
                    )
                    Text(
                        dateOption,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
            HorizontalDivider()
            Text(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp),
                text = stringResource(R.string.subtotal_00, subTotal),
                textAlign = TextAlign.End,
                style = MaterialTheme.typography.titleLarge
            )
        }

        NavButtons(
            onNextButtonClick = onNextButtonClick,
            onCancelButtonClick = onCancelButtonClick,
            enabled = date.isNotEmpty()
        )
    }
}

//@Preview (showBackground = true)
//@Composable
//fun PickupDatePreview() {
//    OrderCupcakeAppTheme {
//        PickupDate()
//    }
//}