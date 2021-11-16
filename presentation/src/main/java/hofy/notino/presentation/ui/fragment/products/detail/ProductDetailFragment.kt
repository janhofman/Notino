package hofy.notino.presentation.ui.fragment.products.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import hofy.notino.presentation.databinding.FragmentProductDetailBinding
import hofy.notino.presentation.ui.BaseFragment

class ProductDetailFragment : BaseFragment<FragmentProductDetailBinding>() {
    private val args by navArgs<ProductDetailFragmentArgs>()
    override fun bind(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentProductDetailBinding {
        return FragmentProductDetailBinding.inflate(inflater, container, false).apply {
            product = args.product
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.title = args.product.name
        onIMainImageInteractionListener?.onMainImageChange(args.product.imageSrc)
    }
}