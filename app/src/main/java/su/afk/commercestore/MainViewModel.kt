package su.afk.commercestore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import su.afk.commercestore.data.network.ProductRepository
import su.afk.commercestore.data.network.service.ProductsService
import su.afk.commercestore.domain.model.Product
import su.afk.commercestore.redux.ApplicationState
import su.afk.commercestore.redux.Store
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val productRepository: ProductRepository,
    val store: Store<ApplicationState>
): ViewModel() {

    fun refreshProduct() {
        viewModelScope.launch {
            val products = productRepository.getAllProducts()
            store.update { applicationState ->
                return@update applicationState.copy(
                    product = products
                )
            }
        }
    }
}