package su.afk.commercestore

import android.util.Log
import androidx.core.view.isGone
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.viewbinding.ViewBinding
import coil.load
import su.afk.commercestore.databinding.RvItemProductBinding
import su.afk.commercestore.domain.model.Product
import su.afk.commercestore.epoxy.ViewBindingKotlinModel
import java.text.NumberFormat

data class ProductEpoxyModel (
    val product: Product?,
) : ViewBindingKotlinModel<RvItemProductBinding>(R.layout.rv_item_product) {

    private val currencyFormat = NumberFormat.getCurrencyInstance()

    override fun RvItemProductBinding.bind() {
        shimmerLayout.isVisible = product == null
        cardView.isInvisible = product == null

        product?.let {
            shimmerLayout.stopShimmer()

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
        } ?: shimmerLayout.startShimmer()

//        {
//            shimmerLayout.startShimmer()
//            Log.d("TAG", "Мерцаем!")
//        }
    }

}