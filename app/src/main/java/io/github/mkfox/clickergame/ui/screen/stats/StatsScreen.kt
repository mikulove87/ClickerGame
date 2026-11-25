package io.github.mkfox.clickergame.ui.screen.stats

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import io.github.mkfox.clickergame.ui.theme.ClickerGameTheme

@Composable
fun StatsScreen() {
    Scaffold() { innerPadding ->
        // TODO: Stats Screen
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(innerPadding).fillMaxSize()
        ) {
            Text("Stats Screen", style = MaterialTheme.typography.displayMedium)
        }
    }
}

@Preview
@Composable
private fun StatsScreenPreview() {
    ClickerGameTheme {
        StatsScreen()
    }
}