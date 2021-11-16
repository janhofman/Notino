package hofy.notino.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import hofy.notino.data.db.dao.FavouriteProductDao
import hofy.notino.data.db.model.DbFavouriteProduct

@Database(entities = [DbFavouriteProduct::class], version = 1)
abstract class NotinoDb : RoomDatabase() {
    abstract fun productDao(): FavouriteProductDao
}