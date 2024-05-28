package su.afk.commercestore

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import su.afk.commercestore.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }



//        val controller = ProductEpoxyController(viewModel = viewModel)
//        binding.epoxyRecycleView.setController(controller)
//        controller.setData(emptyList())
//
//        // обьединение flow
//        combine(
//            viewModel.store.stateFlow.map { it.product },
//            viewModel.store.stateFlow.map { it.favoriteProductIds }
//        ) { listOfProduct, setOfFavoriteProduct ->
//            listOfProduct.map { product ->
//                UiProduct(product = product, isFavorite = setOfFavoriteProduct.contains(product.id))
//            }
//        }.distinctUntilChanged().asLiveData().observe(this){ uiProduct ->
//            controller.setData(uiProduct)
//        }
//        viewModel.refreshProduct()




//        viewModel.productLiveData.observe(this){ listProduct ->
//        viewModel.store.stateFlow.asLiveData().observe(this){ state ->
//            controller.setData(state.product)

//            if(state.product.isEmpty()) {
//                Snackbar.make(binding.root, "Feiled loading", Snackbar.LENGTH_LONG).show()
//            }
//        }



//    private fun setupListener()= with(binding) {
//        cardView.setOnClickListener {
//            tvDescription.apply {
//                isVisible = !isVisible
//            }
//        }
//
//        btnAddCard.setOnClickListener {
//            bthCheckCard.apply {
//                isVisible = !isVisible
//            }
//        }
//
//        var isFavorite = false
//        btnFavorite.setOnClickListener {
//            val imageRes = if(isFavorite) {
//                R.drawable.ic_unfavorite
//            } else {
//                R.drawable.ic_favorite
//            }
//            btnFavorite.setImageResource(imageRes)
//            //btn image
//            isFavorite = !isFavorite
//        }
//
//    }
}