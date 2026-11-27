package io.github.mkfox.clickergame.ui.screen.stats

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.mkfox.clickergame.ui.theme.ClickerGameTheme

@Composable
fun StatsScreen(value: Double = 0.0) {
    Scaffold { innerPadding ->
        // TODO: Stats Screen
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(8.dp)
        ) {
            Text("Stats Screen", style = MaterialTheme.typography.displayMedium)
            Text("$value points")
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