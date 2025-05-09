package com.example.japantravel

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.slideIn
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.japantravel.data.FactDataDirectory
import com.example.japantravel.model.JapanFact
import com.example.japantravel.ui.theme.Shapes
import com.example.japantravel.ui.theme.Typography

@Composable
fun FactScrollList(facts: List<JapanFact>, modifier: Modifier = Modifier) {
    LazyColumn(
    ) {
        items(facts) {
            fact -> FactCard(
                fact = fact,
                )
        }
    }
}

@Composable
fun FactCard(fact: JapanFact, modifier: Modifier = Modifier) {
    var activeFact by remember { mutableStateOf(false) }

    Card (
        onClick = {
            activeFact = !activeFact
                  },
        elevation = CardDefaults.cardElevation(
            defaultElevation = 16.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        border = BorderStroke(1.dp, Color.DarkGray)
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceAround,
            modifier = modifier
                .fillMaxWidth()
                .clip(shape = Shapes.small)
        ) {
            Column(modifier = Modifier.padding(12.dp)) {
                Text(
                    text = stringResource(fact.dayRes),
                    style = Typography.labelSmall,
                )
                Text(
                    text = stringResource(fact.factRes),
                    style = Typography.titleLarge,
                    modifier = Modifier.padding(top = 12.dp)
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(212.dp)
                    .padding(12.dp)
            ) {
                Image(
                    painter = painterResource(fact.imageRes),
                    contentDescription = stringResource(fact.factRes),
                    contentScale = ContentScale.Crop,
                    modifier = modifier.fillMaxSize()
                )
            }
            AnimatedVisibility(
                visible = activeFact
            ) {
                Text(
                    text = stringResource(fact.factDetailsRes),
                    style = Typography.bodyLarge,
                    modifier = Modifier.padding(12.dp)
                )
            }
        }
    }
    Spacer(modifier.height(16.dp))

}