package hofy.notino.data.remote.model

data class ApiProduct(
    val productId: Long,
    val annotation: String?,
    val imageUrl: String?,
    val name: String?,
    val reviewSummary: ApiReviewSummary?,
    val price: ApiPrice?,
    val brand: ApiBrand?

)

data class ApiBrand(val id: String?, val name: String?)
data class ApiPrice(val value: Int?, val currency: String?)
data class ApiReviewSummary(val score: Float?, val averageRating: Float?)
