package hofy.notino.domain.datasource

interface IAddFavouriteProductDataSource {
    suspend fun addFavouriteProduct(id: Long)
}