package cz.airbank.airbanktest.ui.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


sealed interface LaunchState {
    data object None : LaunchState
    data object Loading : LaunchState
    class Success : LaunchState // TODO předat value
    class Failure(val exception: Exception) : LaunchState
}


abstract class BaseViewModel : ViewModel() {

    private val scope = CoroutineScope(Job() + Dispatchers.Default)

    private val _launchState = MutableStateFlow<LaunchState>(LaunchState.None)
    val launchState = _launchState.asStateFlow()

    // TODO MutableStateFlow na stav
    // TODO launch typu Result namísto Unit
    protected fun launch(
        block: (suspend CoroutineScope.() -> Unit),
    ) {
        // TODO řešit chyby CoroutineExceptionHandler...
        scope.launch {
            _launchState.emit(LaunchState.Loading)
            block()
            _launchState.emit(LaunchState.Success())
        }
    }
}