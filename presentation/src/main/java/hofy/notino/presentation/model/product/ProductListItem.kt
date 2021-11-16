package hofy.notino.presentation.model.product

sealed class ProductListItem {
    data class ProductItem(
        val productVO: ProductVO
    ) : ProductListItem()

    data class PageVO(val number: Int) : ProductListItem()

}