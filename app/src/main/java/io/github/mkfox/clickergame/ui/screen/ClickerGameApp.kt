package io.github.mkfox.clickergame.ui.screen

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import io.github.mkfox.clickergame.ui.screen.clicker.ClickerScreen
import io.github.mkfox.clickergame.ui.screen.stats.StatsScreen


@Composable
fun ClickerGameApp() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "/") {
        composable("/") {
            ClickerScreen(onStats = { navController.navigate("/statistics") })
        }
        composable("/statistics") {
            StatsScreen()
        }
    }
}