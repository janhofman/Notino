package hofy.notino.data.db.datasource

import hofy.notino.data.db.NotinoDb
import hofy.notino.domain.datasource.IFavouriteProductIdsDataSource

class FavouriteProductIdsDataSource(private val db: NotinoDb) : IFavouriteProductIdsDataSource {
    override suspend fun getFavourites(): Result<List<Long>> {
        return Result.success(db.productDao().getFavourites())
    }
}