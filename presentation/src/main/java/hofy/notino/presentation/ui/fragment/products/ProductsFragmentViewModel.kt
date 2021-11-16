package hofy.notino.presentation.ui.fragment.products

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import hofy.notino.domain.usecase.product.ChangeFavouriteProductStatusUseCase
import hofy.notino.domain.usecase.product.GetProductsUseCase
import hofy.notino.presentation.model.ViewState
import hofy.notino.presentation.model.product.ProductListItem
import hofy.notino.presentation.model.product.ProductVO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class ProductsFragmentViewModel(
    private val getProductsUseCase: GetProductsUseCase,
    private val changeFavouriteProductStatusUseCase: ChangeFavouriteProductStatusUseCase
) : ViewModel() {

    val viewStateLiveData = MutableLiveData<ViewState<List<ProductListItem>>>()

    fun setRefreshing() {
        viewStateLiveData.value = ViewState.Loading(true)
    }

    fun changeFavouriteState(product: ProductVO) {
        viewModelScope.launch(Dispatchers.IO) {
            changeFavouriteProductStatusUseCase.execute(Pair(product.id, product.isFavourite))
        }
    }

    init {
        viewStateLiveData.value = ViewState.Loading()
    }

    val products: Flow<PagingData<ProductListItem>> =
        Pager(config = PagingConfig(pageSize = 10), initialKey = 1) {
            object : PagingSource<Int, ProductListItem>() {
                override fun getRefreshKey(state: PagingState<Int, ProductListItem>): Int? {
                    return state.anchorPosition
                }

                override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ProductListItem> {
                    val currentKey = if (params.key == null || params.key == 0) 1 else params.key!!
                    val result = getProductsUseCase.execute(currentKey)
                    if (result.isFailure) {
                        return LoadResult.Error(result.exceptionOrNull() ?: Exception("unknown"))
                    } else {
                        val data = (result.getOrNull()?.map {
                            ProductListItem.ProductItem(
                                ProductVO(
                                    it.id,
                                    it.name,
                                    it.brand,
                                    it.description,
                                    it.price,
                                    it.rating,
                                    it.imageSrc,
                                    it.isFavourite
                                )
                            )
                        }) ?: listOf()
                        if (params.key == 1 && data.isNullOrEmpty()) {
                            viewStateLiveData.postValue(ViewState.Empty())
                        } else {
                            viewStateLiveData.postValue(ViewState.Data(data))
                        }

                        val resultData =
                            mutableListOf<ProductListItem>(ProductListItem.PageVO(currentKey))
                        resultData.addAll(data)
                        val previousKey = if (currentKey <= 1) null else currentKey - 1
                        val nextKey = currentKey + 1
                        return LoadResult.Page(
                            resultData,
                            previousKey,
                            nextKey
                        )
                    }

                }

            }
        }.flow.cachedIn(viewModelScope)
}