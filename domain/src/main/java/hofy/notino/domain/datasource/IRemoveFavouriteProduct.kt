package hofy.notino.domain.datasource

interface IRemoveFavouriteProduct {
    suspend fun removeFavouriteProduct(id: Long)
}