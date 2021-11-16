package hofy.notino.presentation.ui.binding

import android.view.View
import android.widget.ImageView
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import hofy.notino.presentation.R
import hofy.notino.presentation.ui.custom.GlideApp

@BindingAdapter("imageSrc")
fun imageSrc(view: ImageView, src: Any?) {
    GlideApp.with(view).load(src).placeholder(R.drawable.ic_logo).into(view)
}

@BindingAdapter("refreshing")
fun refreshing(view: SwipeRefreshLayout, refreshing: Boolean) {
    view.isRefreshing = refreshing
}

@BindingAdapter("visible")
fun visible(view: View, value: Boolean) {
    view.isVisible = value
}