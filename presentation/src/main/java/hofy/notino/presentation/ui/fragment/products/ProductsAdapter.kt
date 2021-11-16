package hofy.notino.presentation.ui.fragment.products

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import hofy.notino.presentation.R
import hofy.notino.presentation.databinding.ItemPageBinding
import hofy.notino.presentation.databinding.ItemProductBinding
import hofy.notino.presentation.model.product.ProductListItem
import hofy.notino.presentation.model.product.ProductVO

class ProductsAdapter(
    private val onFavouriteClickedListener: (ProductVO) -> Unit,
    private val onProductClickListener: (ProductVO) -> Unit
) :
    PagingDataAdapter<ProductListItem, RecyclerView.ViewHolder>(diffCallback) {

    class PageViewHolder(private val binding: ItemPageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ProductListItem.PageVO) {
            binding.page = item
        }
    }

    inner class ProductViewHolder(private val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ProductListItem.ProductItem?) {
            binding.apply {
                product = item?.productVO
                root.setOnClickListener { product?.let { onProductClickListener.invoke(it) } }
                btnAddToCart.setOnClickListener {
                    Snackbar.make(root, R.string.item_added_to_cart, Snackbar.LENGTH_SHORT).show()
                }
                btnFavourite.setOnClickListener {
                    product?.let {
                        onFavouriteClickedListener.invoke(it)
                        it.isFavourite = !it.isFavourite
                        product = it
                        Snackbar.make(
                            root,
                            if (it.isFavourite) {
                                R.string.item_added_to_favourites
                            } else {
                                R.string.item_removed_favourites
                            },
                            Snackbar.LENGTH_SHORT
                        ).show()
                    }

                }
            }

        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ProductViewHolder -> holder.bind(getItem(position) as ProductListItem.ProductItem)
            is PageViewHolder -> {
                holder.bind(getItem(position) as ProductListItem.PageVO)
            }
        }

    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is ProductListItem.ProductItem -> VIEW_TYPE_PRODUCT
            else -> VIEW_TYPE_PAGE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_PRODUCT -> {
                ProductViewHolder(
                    ItemProductBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
            else -> {
                PageViewHolder(
                    ItemPageBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
        }

    }

    companion object {
        const val VIEW_TYPE_PRODUCT = 1
        private const val VIEW_TYPE_PAGE = 2
        val diffCallback = object : DiffUtil.ItemCallback<ProductListItem>() {
            override fun areItemsTheSame(
                oldItem: ProductListItem,
                newItem: ProductListItem
            ): Boolean {
                return if (oldItem is ProductListItem.ProductItem && newItem is ProductListItem.ProductItem) {
                    oldItem.productVO.id == newItem.productVO.id
                } else {
                    oldItem == newItem
                }
            }

            override fun areContentsTheSame(
                oldItem: ProductListItem,
                newItem: ProductListItem
            ): Boolean {
                return oldItem == newItem
            }

        }
    }
}