package hofy.notino.data.db.datasource

import hofy.notino.data.db.NotinoDb
import hofy.notino.data.db.model.DbFavouriteProduct
import hofy.notino.domain.datasource.IAddFavouriteProductDataSource

class AddFavouriteProductsDataSource(private val db: NotinoDb) : IAddFavouriteProductDataSource {
    override suspend fun addFavouriteProduct(id: Long) {
        db.productDao().insert(DbFavouriteProduct(id, true))
    }
}