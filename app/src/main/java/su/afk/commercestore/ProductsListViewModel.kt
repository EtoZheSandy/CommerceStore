package su.afk.commercestore

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import su.afk.commercestore.data.network.ProductRepository
import su.afk.commercestore.redux.ApplicationState
import su.afk.commercestore.redux.Store
import javax.inject.Inject

@HiltViewModel
class ProductsListViewModel @Inject constructor(
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