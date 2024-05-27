package su.afk.commercestore.data.network.service

import retrofit2.Response
import retrofit2.http.GET
import su.afk.commercestore.data.network.model.ProductResponse

interface ProductsService {

    @GET("products")
    suspend fun getAllProducts(): Response<List<ProductResponse>>
}