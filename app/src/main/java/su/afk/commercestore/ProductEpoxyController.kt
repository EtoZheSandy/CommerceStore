package su.afk.commercestore

import com.airbnb.epoxy.EpoxyController
import com.airbnb.epoxy.TypedEpoxyController
import su.afk.commercestore.domain.model.Product

class ProductEpoxyController: TypedEpoxyController<List<Product>>() {

    override fun buildModels(data: List<Product>?) {

        if(data.isNullOrEmpty()) {
            repeat(7) {
                val epoxyId = 1 + it //id должен быть уникальным поэтому мы делаем приставку shimmer-it
                ProductEpoxyModel(product = null).id(epoxyId).addTo(this)
            }
            return
        }

        data.forEach { product ->
            ProductEpoxyModel(product).id(product.id).addTo(this)
        }
    }

}