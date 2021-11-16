package hofy.notino.presentation.ui.binding

import android.widget.TextView
import androidx.databinding.BindingAdapter
import hofy.notino.presentation.R
import hofy.notino.presentation.model.product.ProductListItem

@BindingAdapter("page")
fun page(view: TextView, pageVO: ProductListItem.PageVO) {
    view.text = view.context.getString(R.string.page_format, pageVO.number)
}