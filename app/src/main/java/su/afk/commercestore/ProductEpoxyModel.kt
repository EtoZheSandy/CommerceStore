package su.afk.commercestore

import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.viewbinding.ViewBinding
import coil.load
import su.afk.commercestore.databinding.RvItemProductBinding
import su.afk.commercestore.domain.model.Product
import su.afk.commercestore.epoxy.ViewBindingKotlinModel

data class ProductEpoxyModel (
    val product: Product,
) : ViewBindingKotlinModel<RvItemProductBinding>(R.layout.rv_item_product) {

    override fun RvItemProductBinding.bind() {
        tvTitle.text = product.title
        tvCategory.text = product.category
        tvDescription.text = product.description
        tvPrice.text = product.price.toString()

        // Loading images
        progressBar.isVisible = true
        cardImage.load(data = product.image) {
            listener { request, result ->
                progressBar.isGone = true
            }
        }
    }

}