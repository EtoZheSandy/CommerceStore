package su.afk.commercestore.redux

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import java.lang.Thread.State

class Store<T>(initialState: T) {

    // сохранение состояния переданного типа данных
    private val _stateFlow = MutableStateFlow(initialState)
    val stateFlow: StateFlow<T> = _stateFlow

    private val mutex = Mutex() // блокировка хранилища при чтение или записи

    // принимает параметр лямбду с типом T которая вернет тип T
    suspend fun update(updateBlock: (T) -> T) {
        mutex.withLock { // что бы предотвратить колизию когда больше чем 1 запрос идет на обновление
            // обновить блок с текущем значением состояния
            val newState = updateBlock(_stateFlow.value)
            _stateFlow.value = newState // сохраняем состоние
        }
    }

    suspend fun read(readBlock: (T) -> Unit) {
        mutex.withLock {
            readBlock(_stateFlow.value)
        }
    }
}