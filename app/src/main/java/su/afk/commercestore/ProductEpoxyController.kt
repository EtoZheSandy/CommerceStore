package su.afk.commercestore

import com.airbnb.epoxy.EpoxyController
import com.airbnb.epoxy.TypedEpoxyController
import su.afk.commercestore.domain.model.Product

class ProductEpoxyController: TypedEpoxyController<List<Product>>() {

    override fun buildModels(data: List<Product>?) {
        if(data.isNullOrEmpty()) {
            // todo loading data?
            return
        }

        data.forEach { product ->
            ProductEpoxyModel(product).id(product.id).addTo(this)
        }
    }

}