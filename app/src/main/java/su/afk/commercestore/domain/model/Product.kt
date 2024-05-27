package su.afk.commercestore.domain.model

import su.afk.commercestore.data.network.model.Rating

data class Product (
    val category: String,
    val description: String,
    val id: Int,
    val image: String,
    val price: Double,
    val title: String
)