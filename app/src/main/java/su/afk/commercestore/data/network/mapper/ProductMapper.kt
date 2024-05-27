package su.afk.commercestore.data.network.mapper

import su.afk.commercestore.data.network.model.ProductResponse
import su.afk.commercestore.domain.model.Product
import javax.inject.Inject

class ProductMapper @Inject constructor() {

    fun mapFrom(productResponse: ProductResponse): Product {
        return Product(
            category = productResponse.category,
            description = productResponse.description,
            id = productResponse.id,
            image = productResponse.image,
            price = productResponse.price,
            title = productResponse.title
        )
    }
}