package com.example.ordercupcakeapp.ui.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ordercupcakeapp.R
import com.example.ordercupcakeapp.ui.components.CupcakeDisplay
import com.example.ordercupcakeapp.ui.components.SelectQuantityButtons
import com.example.ordercupcakeapp.ui.theme.OrderCupcakeAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderSummary(
    modifier: Modifier = Modifier,
    quantity: Int,
    flavour: String,
    pickupDate: String,
    subTotal: String,
    onSendButtonClick: (subject: String, summary: String) -> Unit,
    onCancelButtonClick: () -> Unit = {}
) {
    val summary = stringResource(
        R.string.summary,
        quantity,
        flavour,
        pickupDate,
        subTotal
    )

    val subject = stringResource(R.string.your_order_details)

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
            Column(
                modifier = modifier.padding(vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    stringResource(R.string.quantity),
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    stringResource(R.string.cupcakes, quantity),
                    style = MaterialTheme.typography.labelLarge
                )
            }
            HorizontalDivider()

            Column(
                modifier = modifier.padding(vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    stringResource(R.string.flavour),
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    text = flavour.toString(),
                    style = MaterialTheme.typography.labelLarge
                )
            }
            HorizontalDivider()
            Column(
                modifier = modifier.padding(vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    stringResource(R.string.pickup_date),
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    pickupDate.toString(),
                    style = MaterialTheme.typography.labelLarge
                )
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

        Column(
            modifier = modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                onClick = { onSendButtonClick(subject, summary) },
                modifier.fillMaxWidth()
            ) {
                Text(stringResource(R.string.send_order_to_another_app))
            }
            OutlinedButton(
                onClick = onCancelButtonClick,
                modifier.fillMaxWidth()
            ) {
                Text(stringResource(R.string.cancel))
            }
        }

//            AlertDialog(
//                onDismissRequest = {},
//            ) {
//                ElevatedCard {
//
//                }
//            }
    }
}

//@Preview (showBackground = true)
//@Composable
//fun OrderSummaryPreview() {
//    OrderCupcakeAppTheme {
//        OrderSummary()
//    }
//}