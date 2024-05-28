package su.afk.commercestore.data.network

import su.afk.commercestore.data.network.mapper.ProductMapper
import su.afk.commercestore.data.network.service.ProductsService
import su.afk.commercestore.domain.model.Product
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val productsService: ProductsService,
    private val productMapper: ProductMapper
) {

    suspend fun getAllProducts(): List<Product> {
        val response = productsService.getAllProducts().body()

        return response?.let { productResponses ->
            productResponses.map { productMapper.mapFrom(it) }
        }?: emptyList()
    }

}