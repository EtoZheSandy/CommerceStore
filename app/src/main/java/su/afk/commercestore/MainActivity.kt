package su.afk.commercestore

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import coil.load
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import su.afk.commercestore.data.network.mapper.ProductMapper
import su.afk.commercestore.data.network.service.ProductsService
import su.afk.commercestore.databinding.ActivityMainBinding
import su.afk.commercestore.domain.model.Product
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject lateinit var productsService: ProductsService

    @Inject lateinit var productMapper: ProductMapper

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val controller = ProductEpoxyController()
        binding.epoxyRecycleView.setController(controller)
        controller.setData(emptyList())

        lifecycleScope.launch {
            val response = productsService.getAllProducts()
            val domainProducts: List<Product> = response.body()!!.map{
                productMapper.mapFrom(productResponse = it)
            } ?: emptyList()

            controller.setData(domainProducts)

            if(domainProducts.isEmpty()) {
                Snackbar.make(binding.root, "Feiled loading", Snackbar.LENGTH_LONG).show()
            }
        }

//        getData()
//        setupListener()
    }

//    private fun getData() {
//        lifecycleScope.launch {
//            binding.progressBar.isVisible = true
//            val response = productsService.getAllProducts()
//            binding.cardImage.load(
//                data = "https://fakestoreapi.com/img/71-3HjGNDUL._AC_SY879._SX._UX._SY._UY_.jpg"
//            ) {
//                listener{ request, result ->
//                    binding.progressBar.isGone = true
//                }
//            }
//            Log.d("TAG", "${response.body()?.toString()}")
//        }
//    }
//
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