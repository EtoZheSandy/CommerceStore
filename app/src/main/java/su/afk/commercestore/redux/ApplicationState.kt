package su.afk.commercestore.redux

import su.afk.commercestore.domain.model.Product

data class ApplicationState(
    val product: List<Product> = emptyList()
)