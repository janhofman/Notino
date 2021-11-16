package hofy.notino.data.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DbFavouriteProduct(
    @PrimaryKey val productId: Long = 0,
    val isFavourite: Boolean = false
)