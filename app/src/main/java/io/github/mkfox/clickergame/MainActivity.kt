package io.github.mkfox.clickergame

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.mkfox.clickergame.ui.theme.ClickerGameTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ClickerGameTheme {
                ClickerGameApp()
            }
        }
    }
}

@Composable
fun ClickerGameApp() {
    var point by rememberSaveable { mutableStateOf(0L) }
    var gain by rememberSaveable { mutableStateOf(1L) }
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Clicker(
                point = point, gain = gain,
                onClick = { point += gain },
                modifier = Modifier.weight(1f)
            )

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.weight(1f)
            ) {
                Text("Upgrade Menu")
                UpgradeCard(
                    title = "Pointer",
                    cost = 10L,
                    currentPoint = point,
                    onUpgrade = {
                        point -= it
                        gain++
                    },
                    description = "More points per click!"
                )
            }
        }
    }
}

@Composable
fun Clicker(
    point: Long,
    gain: Long,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Point: $point", style = MaterialTheme.typography.titleLarge)
        Text("+$gain per click", style = MaterialTheme.typography.bodyLarge)
        Spacer(Modifier.height(16.dp))
        Button(onClick = onClick) {
            Text("Click here")
        }
    }
}

@Composable
fun UpgradeCard(
    title: String,
    cost: Long,
    currentPoint: Long,
    onUpgrade: (Long) -> Unit,
    description: String = "",
) {
    Card(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
            ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(title, style = MaterialTheme.typography.titleMedium)
                Text("Cost: $cost points", style = MaterialTheme.typography.labelLarge)
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
            cost = 10L,
            currentPoint = 10L,
            onUpgrade = {},
            description = "Descrioption"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AppPreview() {
    ClickerGameTheme {
        ClickerGameApp()
    }
}