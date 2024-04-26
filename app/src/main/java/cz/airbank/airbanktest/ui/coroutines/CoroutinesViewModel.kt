package cz.airbank.airbanktest.ui.coroutines

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.airbank.airbanktest.ui.base.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CoroutinesViewModel : BaseViewModel() {

    // TODO načítání, chybový stav / success stav

    private val _state = MutableStateFlow<String>("-") // Read/write for viewModel
    val state = _state.asStateFlow() // Read only for compose screen

    private val _items = MutableStateFlow(emptyList<String>())
    val items = _items.asStateFlow()

    fun exampleItems() {
        val newList = mutableListOf<String>()
        repeat((1..100).random()) {
            newList.add(
                arrayOf("Tužka", "Stůl", "Sklenice", "Kytka", "Telefon").random()
            )
        }
        _items.update { newList }
    }

    fun exampleCoroutines() {
        launch {
            _state.emit("Start")
            delay(1_000)
            _state.emit("Po 1. vteřině")

            var counter: Int = 0
            repeat(100) {
                delay(100 + it * 5L)
                counter++
                _state.emit("Counter = $counter")
            }
        }
    }

}