package juandanielcuervovilla.me

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * Un único ViewModel para esta aplicación.
 * Mantiene el estado del contador fuera de la interfaz.
 */
class MainViewModel : ViewModel() {
    private val _counter = MutableStateFlow(0)
    val counter: StateFlow<Int> = _counter.asStateFlow()

    fun increment() {
        _counter.value += 1
    }

    fun decrement() {
        if (_counter.value > 0) _counter.value -= 1
    }

    fun reset() {
        _counter.value = 0
    }
}
