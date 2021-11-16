package hofy.notino.domain.datasource


interface IFavouriteProductIdsDataSource {
    suspend fun getFavourites(): Result<List<Long>>
}