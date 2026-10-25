package io.github.mkfox.clickergame.ui.screen.clicker

import androidx.lifecycle.ViewModel
import io.github.mkfox.clickergame.data.Upgrade
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class ClickerUiState(
    val point: Double = 0.0,
    val perClick: Double = 1.0,
    val perSecond: Double = 0.0,
    val upgrades: List<Upgrade> = emptyList(),
)

class ClickerViewModel : ViewModel() {

    // Expose screen UI state
    private val _uiState = MutableStateFlow(ClickerUiState())
    val uiState: StateFlow<ClickerUiState> = _uiState.asStateFlow()

    init {
        addList()
    }

    fun addList() {
        val list = listOf(
            Upgrade("Pointer", "More points per click!", cost = 10.0),
            Upgrade("Auto Clicker", "TODO: Auto Clicker", cost = 50.0),
        )
        _uiState.update { currentState ->
            currentState.copy(
                upgrades = list
            )
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

    fun upgrade(index: Int) {
        when(index) {
            // TODO: increase upgrade cost
            0 -> upgradePointer(uiState.value.upgrades[0].cost)
            1 -> upgradeAutoClicker(uiState.value.upgrades[1].cost)
        }
    }
    fun upgradePointer(cost: Double) {
        _uiState.update { currentState ->
            currentState.copy(
                point = currentState.point - cost,
                perClick = currentState.perClick + 1,
            )
        }
    }

    // TODO: Auto Clicker
    fun upgradeAutoClicker(cost: Double) {
        _uiState.update { currentState ->
            currentState.copy(
                point = currentState.point - cost,
                perSecond = currentState.perSecond + 0.1,
            )
        }
    }
}