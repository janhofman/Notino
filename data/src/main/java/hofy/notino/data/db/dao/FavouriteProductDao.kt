package hofy.notino.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import hofy.notino.data.db.model.DbFavouriteProduct

@Dao
interface FavouriteProductDao {
    @Insert
    suspend fun insert(route: DbFavouriteProduct)

    @Delete
    suspend fun delete(route: DbFavouriteProduct)

    @Query("SELECT DbFavouriteProduct.productId FROM DbFavouriteProduct WHERE DbFavouriteProduct.isFavourite = 1")
    suspend fun getFavourites(): List<Long>
}