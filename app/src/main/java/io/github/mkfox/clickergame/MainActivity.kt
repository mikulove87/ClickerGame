package io.github.mkfox.clickergame

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import io.github.mkfox.clickergame.ui.screen.ClickerGameApp
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