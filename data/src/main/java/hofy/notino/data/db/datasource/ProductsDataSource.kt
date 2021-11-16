package hofy.notino.data.db.datasource

import hofy.notino.data.BuildConfig
import hofy.notino.data.remote.NotinoApi
import hofy.notino.data.remote.safeApiCall
import hofy.notino.domain.datasource.IProductsDataSource
import hofy.notino.domain.model.ProductDO
import java.util.*

class ProductsDataSource(private val api: NotinoApi) : IProductsDataSource {
    override suspend fun getProducts(page: Int): Result<List<ProductDO>> {
        return safeApiCall {
            val response = api.products()
            if (response.isSuccessful) {
                Result.success(response.body()?.products?.map {
                    ProductDO(
                        it.productId,
                        it.name.orEmpty(),
                        it.brand?.name.orEmpty(),
                        it.annotation.orEmpty(),
                        "${it.price?.value.toString()} ${Currency.getInstance(it.price?.currency).symbol}",
                        it.reviewSummary?.averageRating ?: 0f,
                        if (it.imageUrl != null) BuildConfig.BASE_IMAGE_URL + it.imageUrl else it.imageUrl,
                        false
                    )
                } ?: listOf())
            } else {
                Result.failure(Exception("unknown"))
            }
        }
    }
}