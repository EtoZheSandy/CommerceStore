package su.afk.commercestore

import android.util.Log
import androidx.core.view.isGone
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.viewbinding.ViewBinding
import coil.load
import su.afk.commercestore.databinding.RvItemProductBinding
import su.afk.commercestore.domain.model.Product
import su.afk.commercestore.domain.model.UiProduct
import su.afk.commercestore.epoxy.ViewBindingKotlinModel
import java.text.NumberFormat

data class ProductEpoxyModel (
    val uiProduct: UiProduct?,
    val onFavoriteIconClicked: (Int) -> Unit
) : ViewBindingKotlinModel<RvItemProductBinding>(R.layout.rv_item_product) {

    private val currencyFormat = NumberFormat.getCurrencyInstance()

    override fun RvItemProductBinding.bind() {
        shimmerLayout.isVisible = uiProduct == null
        cardView.isInvisible = uiProduct == null

        uiProduct?.let {
            shimmerLayout.stopShimmer()

            tvTitle.text = uiProduct.product.title
            tvCategory.text = uiProduct.product.category
            tvDescription.text = uiProduct.product.description
            tvPrice.text = uiProduct.product.price.toString()

            //favorite icon
            val imageRes =if(uiProduct.isFavorite) {
                R.drawable.ic_favorite
            } else {
                R.drawable.ic_unfavorite
            }
            btnFavorite.setImageResource(imageRes)
            btnFavorite.setOnClickListener {
                onFavoriteIconClicked(uiProduct.product.id)
            }

            // Loading images
            progressBar.isVisible = true
            cardImage.load(data = uiProduct.product.image) {
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