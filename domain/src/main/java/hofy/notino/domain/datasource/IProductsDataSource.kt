package hofy.notino.domain.datasource

import hofy.notino.domain.model.ProductDO

interface IProductsDataSource {
    suspend fun getProducts(page: Int): Result<List<ProductDO>>
}