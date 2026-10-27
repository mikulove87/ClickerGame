package io.github.mkfox.clickergame.ui.screen.clicker

import android.content.Intent
import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.android.gms.oss.licenses.v2.OssLicensesMenuActivity
import io.github.mkfox.clickergame.R
import io.github.mkfox.clickergame.data.Upgrade
import io.github.mkfox.clickergame.ui.theme.ClickerGameTheme

@Composable
fun ClickerScreen(viewModel: ClickerViewModel = viewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val activity = LocalActivity.current
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            var expanded by remember { mutableStateOf(false) }
            TopAppBar(
                title = {},
                actions = {
                    Box {
                        IconButton(onClick = {
                            expanded = !expanded
                        }) {
                            Icon(
                                painter = painterResource(R.drawable.more_vert_24px),
                                contentDescription = "More"
                            )
                        }
                        DropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false },
                            shape = RoundedCornerShape(16.dp)
                        ) {
                            DropdownMenuItem(
                                text = { Text("Licenses") },
                                leadingIcon = {
                                    Icon(
                                        painterResource(R.drawable.article_24px),
                                        contentDescription = null
                                    )
                                },
                                onClick = {
                                    val intent =
                                        Intent(activity, OssLicensesMenuActivity::class.java)
                                    activity?.startActivity(intent)
                                    expanded = false
                                }
                            )
                        }
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Clicker(
                point = uiState.point,
                perClick = uiState.perClick,
                perSecond = uiState.perSecond,
                onClick = { viewModel.click() },
                modifier = Modifier.weight(1f)
            )

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.weight(1f)
            ) {
                Text("Upgrades")
                LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    itemsIndexed(uiState.upgrades) { index, item ->
                        UpgradeCard(
                            upgrade = item,
                            currentPoint = uiState.point,
                            onUpgrade = {
                                viewModel.upgrade(index)
                            },
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun Clicker(
    point: Double,
    perClick: Double,
    perSecond: Double,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Point: %.0f".format(point), style = MaterialTheme.typography.titleLarge)
        Text("+%.0f per click".format(perClick))
        Text("+%.1f/s".format(perSecond))
        Spacer(Modifier.height(16.dp))

        val size = ButtonDefaults.LargeContainerHeight
        Button(
            onClick = onClick,
            contentPadding = ButtonDefaults.contentPaddingFor(size)
        ) {
            Text("Click here!", style = ButtonDefaults.textStyleFor(size))
        }
    }
}

@Composable
fun UpgradeCard(
    upgrade: Upgrade,
    currentPoint: Double,
    onUpgrade: () -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(upgrade.title, style = MaterialTheme.typography.titleMedium)
                Text(
                    "Cost: %.0f points".format(upgrade.cost),
                    style = MaterialTheme.typography.labelLarge
                )
                if (upgrade.description != null) {
                    Text(upgrade.description, style = MaterialTheme.typography.bodySmall)
                }
            }
            OutlinedButton(
                onClick = { onUpgrade() },
                enabled = currentPoint >= upgrade.cost,
                shapes = ButtonDefaults.shapes()
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
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            UpgradeCard(
                Upgrade(
                    title = "Pointer",
                    description = "Description",
                    cost = 10.0
                ),
                currentPoint = 10.0,
                onUpgrade = {},

                )
            UpgradeCard(
                Upgrade(
                    title = "No description",
                    cost = 10.0
                ),
                currentPoint = 10.0,
                onUpgrade = {}
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppPreview() {
    ClickerGameTheme {
        ClickerScreen()
    }
}