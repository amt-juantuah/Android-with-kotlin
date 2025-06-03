package com.example.japanfacts.screens

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.japanfacts.data.FactDataResources
import com.example.japanfacts.model.Fact
import com.example.japanfacts.ui.theme.JapanFactsTheme


@Composable
fun FactList() {

}

@Composable
fun FactCard(fact: Fact, modifier: Modifier = Modifier) {
    Column {
        FactTitle(
            factDay = fact.factDay,
            titleRes = fact.titleRes,
            modifier = modifier
        )
        FactImage(
            imageRes = fact.imageRes,
            modifier = modifier
        )
    }
}

@Composable
fun FactImage(
    @DrawableRes imageRes: Int,
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(imageRes),
        contentDescription = "null",
        contentScale = ContentScale.Crop,
        modifier = modifier
//            .fillMaxWidth()
//            .height(213.dp)
    )
}

@Composable
fun FactTitle(
    @StringRes factDay: Int,
    @StringRes titleRes: Int,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = stringResource(factDay),
            style = MaterialTheme.typography.labelSmall
        )
        Text(
            text = stringResource(titleRes),
            style = MaterialTheme.typography.labelLarge
        )
    }
}

@Preview(showBackground = true)
@Composable
fun FactCardPreview() {
    JapanFactsTheme(darkTheme = false) {
        FactCard(FactDataResources.facts.get(2))
    }
}
