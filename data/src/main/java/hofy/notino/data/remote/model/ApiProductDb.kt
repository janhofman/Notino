package hofy.notino.data.remote.model

import com.google.gson.annotations.SerializedName

data class ApiProductDb(@SerializedName("vpProductByIds") val products: List<ApiProduct>)
