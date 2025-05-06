package com.example.superhero

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.superhero.model.Hero

@Composable
fun HeroList(
    heroList: List<Hero>,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier
            .padding(16.dp)
    ) {
        items(heroList) {
            hero -> HeroCard(hero)
        }
    }
}

@Composable
fun HeroCard(heroItem: Hero, modifier: Modifier = Modifier) {
    Card(
        elevation = CardDefaults.cardElevation(2.dp),
        modifier = modifier
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.medium)
            .padding(bottom = 16.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp).fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            HeroText(heroName = heroItem.nameRes, heroDescription = heroItem.descriptionRes)
            Spacer(modifier = Modifier.width(16.dp))
            HeroImage(heroImage = heroItem.imageRes)
        }
    }
}

@Composable
fun HeroImage(@DrawableRes heroImage: Int, modifier: Modifier = Modifier) {
        Image(
            painter = painterResource(heroImage),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = modifier
                .size(72.dp)
                .clip(MaterialTheme.shapes.small)
        )
}

@Composable
fun HeroText(@StringRes heroName: Int, @StringRes heroDescription: Int, modifier: Modifier = Modifier) {
    Column(modifier = modifier.width(216.dp)) {
        Text(
            text = stringResource(heroName),
            style = MaterialTheme.typography.displaySmall
        )
        Text(
            text = stringResource(heroDescription),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}