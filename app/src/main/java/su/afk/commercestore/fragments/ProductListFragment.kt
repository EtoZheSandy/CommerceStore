package su.afk.commercestore.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import su.afk.commercestore.ProductEpoxyController
import su.afk.commercestore.ProductsListViewModel
import su.afk.commercestore.databinding.FragmentProductsListBinding
import su.afk.commercestore.domain.model.UiProduct

@AndroidEntryPoint
class ProductListFragment: Fragment() {

    private var _binding: FragmentProductsListBinding? = null
    private val binding by lazy { _binding!! }

    private val viewModel: ProductsListViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) : View {
        _binding = FragmentProductsListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
        }.distinctUntilChanged().asLiveData().observe(viewLifecycleOwner){ uiProduct ->
            controller.setData(uiProduct)
        }
        viewModel.refreshProduct()
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}