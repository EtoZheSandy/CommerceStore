package su.afk.commercestore.domain.model

data class UiProduct(
    val product: Product,
    val isFavorite: Boolean = false
)
