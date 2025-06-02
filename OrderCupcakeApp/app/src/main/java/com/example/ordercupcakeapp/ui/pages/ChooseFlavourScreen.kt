package com.example.ordercupcakeapp.ui.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ordercupcakeapp.R
import com.example.ordercupcakeapp.data.DataSource
import com.example.ordercupcakeapp.ui.components.CupcakeDisplay
import com.example.ordercupcakeapp.ui.components.NavButtons
import com.example.ordercupcakeapp.ui.components.SelectQuantityButtons
import com.example.ordercupcakeapp.ui.theme.OrderCupcakeAppTheme

@Composable
fun ChooseFlavour(
    modifier: Modifier = Modifier,
    chooseOrder: (flavour: String) -> Unit,
    flavour: String,
    subTotal: String,
    flavourOptions: List<String>,
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
            flavourOptions.map { option ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(
                        selected = flavour == option,
                        onClick = { chooseOrder((option)) }
                    )
                    Text(
                        text = option,
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
            enabled = flavour.isNotEmpty()
        )
    }

}

@Preview (showBackground = true)
@Composable
fun ChooseFlavourPreview() {
    OrderCupcakeAppTheme {
//        ChooseFlavour(flavour = "")
    }
}