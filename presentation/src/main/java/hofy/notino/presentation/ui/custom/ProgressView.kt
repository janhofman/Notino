package hofy.notino.presentation.ui.custom

import android.content.Context
import android.util.AttributeSet
import android.view.animation.AnimationUtils
import hofy.notino.presentation.R

class ProgressView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : androidx.appcompat.widget.AppCompatImageView(context, attrs) {
    init {
        setImageResource(R.drawable.ic_logo)
        val anim = AnimationUtils.loadAnimation(context, R.anim.rotation)
        startAnimation(anim)
    }
}