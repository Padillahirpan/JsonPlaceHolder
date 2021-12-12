package id.irpn.kmprn.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

/**
 * Created by irpanpadillah on 11/12/21
 * Email: padillahirpan8@gmail.com
 */

data class PostResponse(
    @SerializedName("id")
    var id: Int = 0,
    @SerializedName("userId")
    var userId: Int = 0,
    @SerializedName("title")
    var title: String? = null,
    @SerializedName("body")
    var body: String? = null
)