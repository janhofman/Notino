package hofy.notino.presentation.model

sealed class ViewState<DATA> {
    data class Loading<T>(val refresh: Boolean = false) : ViewState<T>()
    class Empty<T> : ViewState<T>()
    data class Error<T>(val e: Throwable?) : ViewState<T>()
    data class Data<T>(val data: T) : ViewState<T>()

    fun getDataOrNull(): DATA? = if (this is Data) {
        data
    } else {
        null
    }

    fun isLoading() = this is Loading && !refresh
    fun isRefresh() = this is Loading && refresh
    fun isError() = this is Error
    fun isData() = this is Data
    fun isEmpty() = this is Empty
}
