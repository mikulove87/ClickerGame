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
import io.github.mkfox.clickergame.ui.screen.ClickerUiState
import io.github.mkfox.clickergame.ui.theme.ClickerGameTheme

@Composable
fun StatsScreen(uiState: ClickerUiState) {
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
            Text("${uiState.point} points")
            Text("${uiState.pointTotal} points total")
            Text("${uiState.click} clicks total")
        }
    }
}

@Preview
@Composable
private fun StatsScreenPreview() {
    val sample = ClickerUiState(
        point = 12.3,
        pointTotal = 24.6,
        click = 64
    )
    ClickerGameTheme {
        StatsScreen(sample)
    }
}