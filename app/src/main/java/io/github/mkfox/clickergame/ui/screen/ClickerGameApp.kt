package io.github.mkfox.clickergame.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import io.github.mkfox.clickergame.ui.screen.clicker.ClickerScreen
import io.github.mkfox.clickergame.ui.screen.stats.StatsScreen


@Composable
fun ClickerGameApp(viewModel: ClickerViewModel = viewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "/") {
        composable("/") {
            ClickerScreen(
                viewModel = viewModel,
                onStats = { navController.navigate("/statistics") })
        }
        composable("/statistics") {
            StatsScreen(uiState = uiState)
        }
    }
}