package id.irpn.kmprn.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

/**
 * Created by irpanpadillah on 11/12/21
 * Email: padillahirpan8@gmail.com
 */

data class PostCommentResponse(
    @SerializedName("id")
    var id: Int = 0,
    @SerializedName("postId")
    var postId: Int = 0,
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("email")
    var email: String? = null,
    @SerializedName("body")
    var body: String? = null
)