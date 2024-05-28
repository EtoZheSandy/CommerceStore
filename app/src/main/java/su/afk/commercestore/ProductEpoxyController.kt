package su.afk.commercestore

import androidx.lifecycle.viewModelScope
import com.airbnb.epoxy.TypedEpoxyController
import kotlinx.coroutines.launch
import su.afk.commercestore.domain.model.UiProduct

class ProductEpoxyController(
    private val viewModel: ProductsListViewModel
): TypedEpoxyController<List<UiProduct>>() {

    override fun buildModels(data: List<UiProduct>?) {
        if(data.isNullOrEmpty()) {
            repeat(7) {
                val epoxyId = 1 + it //id должен быть уникальным поэтому мы делаем приставку shimmer-it
                ProductEpoxyModel(
                    uiProduct = null,
                    onFavoriteIconClicked = ::onFavoriteClicked
                ).id(epoxyId).addTo(this)
            }
            return
        }

        data.forEach { uiProduct ->
            ProductEpoxyModel(
                uiProduct = uiProduct,
                onFavoriteIconClicked = ::onFavoriteClicked
            ).id(uiProduct.product.id).addTo(this)
        }
    }

    // функция клика по избранному
    private fun onFavoriteClicked(selectedProductId: Int) {
        viewModel.viewModelScope.launch {
            // обновляем state
            viewModel.store.update { state ->
                // получаем все идентификаторы избранного
                val currentFavoriteIds = state.favoriteProductIds
                // содержит ли текущий state уже выбарнный id
                val newFavoriteIds = if(currentFavoriteIds.contains(selectedProductId)) {
                    // если содержит то фильтруем их исключая выбранный id
                    currentFavoriteIds.filter { it != selectedProductId }.toSet()
                } else {
                    // если нету то добавляем его в наш state
                    currentFavoriteIds + setOf(selectedProductId)
                }
                // обновляем state заменяя favoriteProductIds новым списком
                return@update state.copy(favoriteProductIds = newFavoriteIds)
            }
        }
    }

}