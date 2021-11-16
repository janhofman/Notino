package hofy.notino.domain.usecase.product

import hofy.notino.domain.datasource.IAddFavouriteProductDataSource
import hofy.notino.domain.datasource.IRemoveFavouriteProduct
import hofy.notino.domain.usecase.UseCase

class ChangeFavouriteProductStatusUseCase(
    private val addDataSource: IAddFavouriteProductDataSource,
    private val deleteDataSource: IRemoveFavouriteProduct
) : UseCase<Pair<Long, Boolean>, Unit>() {
    override suspend fun execute(params: Pair<Long, Boolean>): Result<Unit> {
        return Result.success(
            if (params.second) {
                addDataSource.addFavouriteProduct(params.first)
            } else {
                deleteDataSource.removeFavouriteProduct(params.first)
            }
        )

    }
}