package hofy.notino.data.db.datasource

import hofy.notino.data.db.NotinoDb
import hofy.notino.data.db.model.DbFavouriteProduct
import hofy.notino.domain.datasource.IRemoveFavouriteProduct

class RemoveFavouriteProductDataSource(private val db: NotinoDb) : IRemoveFavouriteProduct {
    override suspend fun removeFavouriteProduct(id: Long) {
        db.productDao().delete(DbFavouriteProduct(id, true))
    }
}