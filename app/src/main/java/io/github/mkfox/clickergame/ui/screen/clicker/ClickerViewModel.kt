package io.github.mkfox.clickergame.ui.screen.clicker

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.mkfox.clickergame.data.InitialUpgrades
import io.github.mkfox.clickergame.data.Upgrade
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

data class ClickerUiState(
    val point: Double = 0.0,
    val perClick: Double = 1.0,
    val perSecond: Double = 0.0,
    val upgrades: List<Upgrade> = InitialUpgrades,
)

class ClickerViewModel : ViewModel() {

    // Expose screen UI state
    private val _uiState = MutableStateFlow(ClickerUiState())
    val uiState: StateFlow<ClickerUiState> = _uiState.asStateFlow()

    init {
        // TODO: Stop when background
        viewModelScope.launch {
            while (true) {
                onSecond()
                delay(1.seconds)
            }
        }
    }

    // Handle business logic
    fun click() {
        _uiState.update { currentState ->
            currentState.copy(
                point = currentState.point + currentState.perClick
            )
        }
    }

    fun onSecond() {
        _uiState.update { currentState ->
            currentState.copy(
                point = currentState.point + currentState.perSecond
            )
        }
    }

    fun upgrade(index: Int) {
        val upgrade = uiState.value.upgrades[index]

        usePoint(upgrade.cost)
        when (index) {
            0 -> {
                updateCost(upgrade, 1.2)
                updateGain(perClick = 1.0)
            }

            1 -> {
                updateCost(upgrade, 1.3)
                updateGain(perSecond = 0.2)
            }
        }
    }

    fun usePoint(cost: Double) {
        _uiState.update { currentState ->
            currentState.copy(
                point = currentState.point - cost
            )
        }
    }

    fun updateCost(upgrade: Upgrade, multiply: Double) {
        _uiState.update { currentState ->
            val upgrades = currentState.upgrades.map {
                if (it == upgrade) it.copy(cost = it.cost * multiply) else it
            }
            currentState.copy(
                upgrades = upgrades
            )
        }
    }

    fun updateGain(
        perClick: Double = 0.0,
        perSecond: Double = 0.0
    ) {
        _uiState.update { currentState ->
            currentState.copy(
                perClick = currentState.perClick + perClick,
                perSecond = currentState.perSecond + perSecond
            )
        }
    }
}