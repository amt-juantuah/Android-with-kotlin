package com.example.ordercupcakeapp.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.ordercupcakeapp.R

@Composable
fun NavButtons(
    modifier: Modifier = Modifier,
    onNextButtonClick: () -> Unit = {},
    onCancelButtonClick: () -> Unit = {},
    enabled: Boolean = false
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        OutlinedButton(
            onClick = onCancelButtonClick,
            modifier.weight(1f)
        ) {
            Text(stringResource(R.string.cancel))
        }

        Button(
            onClick = onNextButtonClick,
            modifier.weight(1f),
            enabled = enabled,
        ) {
            Text(stringResource(R.string.next))
        }
    }
}