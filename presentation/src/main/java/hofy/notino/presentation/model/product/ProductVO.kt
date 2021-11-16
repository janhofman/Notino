package hofy.notino.presentation.model.product

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductVO(
    val id: Long,
    val name: String,
    val brand: String,
    val description: String,
    val price: String,
    val rating: Float,
    val imageSrc: String? = null,
    var isFavourite: Boolean = false
) : Parcelable