package hofy.notino.presentation.ui.fragment.products

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import hofy.notino.presentation.databinding.FragmentProductsBinding
import hofy.notino.presentation.model.product.ProductVO
import hofy.notino.presentation.ui.BaseFragment
import hofy.notino.presentation.ui.fragment.products.ProductsAdapter.Companion.VIEW_TYPE_PRODUCT
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProductsFragment : BaseFragment<FragmentProductsBinding>() {

    private val viewModel by viewModel<ProductsFragmentViewModel>()

    override fun bind(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentProductsBinding {
        return FragmentProductsBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            vm = viewModel
            val productsAdapter = ProductsAdapter({
                viewModel.changeFavouriteState(it)
            }, {
                findNavController().navigate(
                    ProductsFragmentDirections.actionNavigateToProductDetail(
                        ProductVO(
                            it.id,
                            it.name,
                            it.brand,
                            it.description,
                            it.price,
                            it.rating,
                            it.imageSrc,
                            it.isFavourite
                        )
                    )
                )
            })
            val adapter = productsAdapter.withLoadStateHeaderAndFooter(
                ProductLoadAdapter(),
                ProductLoadAdapter()
            )
            val layoutManager = GridLayoutManager(requireContext(), 2)
            layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    val viewType = productsAdapter.getItemViewType(position)
                    return if (viewType == VIEW_TYPE_PRODUCT) 1 else 2
                }
            }
            recyclerProducts.layoutManager = layoutManager
            recyclerProducts.adapter = adapter
            refreshLayout.setOnRefreshListener {
                viewModel.setRefreshing()
                productsAdapter.refresh()
            }

            lifecycleScope.launch {
                viewModel.products.collectLatest {
                    productsAdapter.submitData(it)
                }
            }

        }
    }
}