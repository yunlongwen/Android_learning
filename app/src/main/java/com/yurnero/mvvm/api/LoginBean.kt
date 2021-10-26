import com.google.gson.annotations.SerializedName
import com.yurnero.network.beans.BaseResponse

data class LoginResponse(@SerializedName("result") val result: LoginBean)

data class LoginBean(@SerializedName("data") val data: LoginData)

data class LoginData(
    @SerializedName("id") val id: String,
    @SerializedName("username") val username: String,
    @SerializedName("email") val email: String,
    @SerializedName("sessionToken") val sessionToken: String
)

data class LoginParam(private val username: String, private val password: String)