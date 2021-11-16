package hofy.notino.domain.model

data class ProductDO(
    val id: Long,
    val name: String,
    val brand: String,
    val description: String,
    val price: String,
    val rating: Float,
    val imageSrc: String?,
    var isFavourite: Boolean
)