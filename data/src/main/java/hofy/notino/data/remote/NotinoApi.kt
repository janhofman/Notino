package hofy.notino.data.remote

import hofy.notino.data.remote.model.ApiProductDb
import retrofit2.Response
import retrofit2.http.GET

interface NotinoApi {
    @GET("db")
    suspend fun products(): Response<ApiProductDb>
}