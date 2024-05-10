package cz.airbank.airbanktest.ui.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


sealed interface LaunchState {
    data object None : LaunchState
    data object Loading : LaunchState
    class Success : LaunchState // TODO předat value
    class Failure(
        val throwable: Throwable,
        val retry: () -> Unit,
    ) : LaunchState
}


abstract class BaseViewModel : ViewModel() {

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Default)

    private val _launchState = MutableStateFlow<LaunchState>(LaunchState.None)
    val launchState = _launchState.asStateFlow()

    // TODO launch typu Result namísto Unit
    protected fun launch(
        block: (suspend CoroutineScope.() -> Unit),
    ) {
        scope.launch(
            CoroutineExceptionHandler { _, throwable ->
                _launchState.tryEmit(
                    LaunchState.Failure(
                        throwable = throwable,
                        retry = { launch(block) },
                    )
                )
            }
        ) {
            _launchState.emit(LaunchState.Loading)
            block()
            _launchState.emit(LaunchState.Success())
        }
    }
}