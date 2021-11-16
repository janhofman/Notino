package hofy.notino.domain.usecase.product

import hofy.notino.domain.datasource.IFavouriteProductIdsDataSource
import hofy.notino.domain.datasource.IProductsDataSource
import hofy.notino.domain.model.ProductDO
import hofy.notino.domain.usecase.UseCase

class GetProductsUseCase(
    private val dataSource: IProductsDataSource,
    private val favouriteIdsDataSource: IFavouriteProductIdsDataSource
) :
    UseCase<Int, List<ProductDO>>() {
    override suspend fun execute(params: Int): Result<List<ProductDO>> {
        val result = dataSource.getProducts(params)
        val favouriteIds = favouriteIdsDataSource.getFavourites().getOrNull()
        favouriteIds?.let {
            result.getOrNull()?.filter { favouriteIds.contains(it.id) }?.forEach {
                it.isFavourite = true
            }
        }
        return result
    }
}