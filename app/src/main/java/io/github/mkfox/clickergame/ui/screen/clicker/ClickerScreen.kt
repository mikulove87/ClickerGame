package io.github.mkfox.clickergame.ui.screen.clicker

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import io.github.mkfox.clickergame.ui.theme.ClickerGameTheme

@Composable
fun ClickerScreen(viewModel: ClickerViewModel = viewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Clicker(
                point = uiState.point, gain = uiState.gain,
                onClick = { viewModel.click() },
                modifier = Modifier.weight(1f)
            )

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.weight(1f)
            ) {
                Text("Upgrade Menu")
                UpgradeCard(
                    title = "Pointer",
                    cost = uiState.pointerCost,
                    currentPoint = uiState.point,
                    onUpgrade = {
                        viewModel.upgradePointer()
                    },
                    description = "More points per click!"
                )
                UpgradeCard(
                    title = "Auto Clicker",
                    cost = uiState.autoClickerCost,
                    currentPoint = uiState.point,
                    onUpgrade = {
                        viewModel.upgradeAutoClicker()
                    },
                    description = "TODO: Auto Clicker"
                )
            }
        }
    }
}

@Composable
fun Clicker(
    point: Double,
    gain: Double,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Point: %.0f".format(point), style = MaterialTheme.typography.titleLarge)
        Text("+%.0f per click".format(gain), style = MaterialTheme.typography.bodyLarge)
        Spacer(Modifier.height(16.dp))
        Button(onClick = onClick) {
            Text("Click here")
        }
    }
}

@Composable
fun UpgradeCard(
    title: String,
    cost: Double,
    currentPoint: Double,
    onUpgrade: (Double) -> Unit,
    description: String = "",
) {
    Card(modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp)) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(title, style = MaterialTheme.typography.titleMedium)
                Text("Cost: %.0f points".format(cost), style = MaterialTheme.typography.labelLarge)
                Text(description, style = MaterialTheme.typography.bodySmall)
            }
            OutlinedButton(
                onClick = { onUpgrade(cost) },
                enabled = currentPoint >= cost
            ) {
                Text("Upgrade")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UpgradeCardPreview() {
    ClickerGameTheme {
        UpgradeCard(
            title = "Pointer",
            cost = 10.0,
            currentPoint = 10.0,
            onUpgrade = {},
            description = "Descrioption"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AppPreview() {
    ClickerGameTheme {
        ClickerScreen()
    }
}