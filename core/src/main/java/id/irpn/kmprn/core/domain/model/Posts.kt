package id.irpn.kmprn.core.domain.model

import com.google.gson.annotations.SerializedName

/**
 * Created by irpanpadillah on 11/12/21
 * Email: padillahirpan8@gmail.com
 */

data class Posts(
    @SerializedName("id")
    val id: Int,
    @SerializedName("userId")
    var userId: Int = 0,
    @SerializedName("title")
    var title: String? = null,
    @SerializedName("body")
    var body: String? = null,
    @SerializedName("username")
    val name: String? = null,
    @SerializedName("userCompany")
    val userCompany: String? = null
)