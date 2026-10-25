package io.github.mkfox.clickergame.ui.screen.clicker

import androidx.lifecycle.ViewModel
import io.github.mkfox.clickergame.data.Upgrade
import io.github.mkfox.clickergame.data.Upgrades
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class ClickerUiState(
    val point: Double = 0.0,
    val perClick: Double = 1.0,
    val perSecond: Double = 0.0,
    val upgrades: List<Upgrade> = Upgrades,
)

class ClickerViewModel : ViewModel() {

    // Expose screen UI state
    private val _uiState = MutableStateFlow(ClickerUiState())
    val uiState: StateFlow<ClickerUiState> = _uiState.asStateFlow()

    // Handle business logic
    fun click() {
        _uiState.update { currentState ->
            currentState.copy(
                point = currentState.point + currentState.perClick
            )
        }
    }

    fun upgrade(index: Int) {
        val upgrade = uiState.value.upgrades[index]
        when (index) {
            0 -> upgradePointer(upgrade)
            1 -> upgradeAutoClicker(upgrade)
        }
    }

    fun upgradePointer(upgrade: Upgrade) {
        _uiState.update { currentState ->
            val upgrades = currentState.upgrades.map {
                if (it == upgrade) it.copy(cost = it.cost * 1.2) else it
            }
            currentState.copy(
                point = currentState.point - upgrade.cost,
                perClick = currentState.perClick + 1,
                upgrades = upgrades
            )
        }
    }

    // TODO: Auto Clicker
    fun upgradeAutoClicker(upgrade: Upgrade) {
        _uiState.update { currentState ->
            val upgrades = currentState.upgrades.map {
                if (it == upgrade) it.copy(cost = it.cost * 1.3) else it
            }
            currentState.copy(
                point = currentState.point - upgrade.cost,
                perSecond = currentState.perSecond + 0.2,
                upgrades = upgrades
            )
        }
    }
}