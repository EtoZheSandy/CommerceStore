package su.afk.commercestore

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import coil.load
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import su.afk.commercestore.data.network.mapper.ProductMapper
import su.afk.commercestore.data.network.service.ProductsService
import su.afk.commercestore.databinding.ActivityMainBinding
import su.afk.commercestore.domain.model.Product
import su.afk.commercestore.domain.model.UiProduct
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val controller = ProductEpoxyController(viewModel = viewModel)
        binding.epoxyRecycleView.setController(controller)
        controller.setData(emptyList())

        // обьединение flow
        combine(
            viewModel.store.stateFlow.map { it.product },
            viewModel.store.stateFlow.map { it.favoriteProductIds }
        ) { listOfProduct, setOfFavoriteProduct ->
            listOfProduct.map { product ->
                UiProduct(product = product, isFavorite = setOfFavoriteProduct.contains(product.id))
            }
        }.distinctUntilChanged().asLiveData().observe(this){ uiProduct ->
            controller.setData(uiProduct)
        }


//        viewModel.productLiveData.observe(this){ listProduct ->
//        viewModel.store.stateFlow.asLiveData().observe(this){ state ->
//            controller.setData(state.product)

//            if(state.product.isEmpty()) {
//                Snackbar.make(binding.root, "Feiled loading", Snackbar.LENGTH_LONG).show()
//            }
//        }
        viewModel.refreshProduct()
    }


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