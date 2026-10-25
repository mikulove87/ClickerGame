package io.github.mkfox.clickergame.ui.screen.clicker

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class ClickerUiState(
    val point: Double = 0.0,
    val gain: Double = 1.0,
    val pointerCost: Double = 10.0,
    val autoClickerCost: Double = 100.0
)

class ClickerViewModel : ViewModel() {

    // Expose screen UI state
    private val _uiState = MutableStateFlow(ClickerUiState())
    val uiState: StateFlow<ClickerUiState> = _uiState.asStateFlow()

    // Handle business logic
    fun click() {
        _uiState.update { currentState ->
            currentState.copy(
                point = currentState.point + currentState.gain
            )
        }
    }

    fun upgradePointer() {
        _uiState.update { currentState ->
            currentState.copy(
                point = currentState.point - currentState.pointerCost,
                gain = currentState.gain + 1,
                pointerCost = currentState.pointerCost * 1.2
            )
        }
    }

    // TODO: Auto Clicker
    fun upgradeAutoClicker() {
        _uiState.update { currentState ->
            currentState.copy(
                point = currentState.point - currentState.autoClickerCost
            )
        }
    }
}