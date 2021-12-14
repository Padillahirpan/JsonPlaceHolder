package id.irpn.kmprn.core.domain.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

/**
 * Created by irpanpadillah on 11/12/21
 * Email: padillahirpan8@gmail.com
 */

@Parcelize
data class Posts(
    @SerializedName("id")
    val id: Int,
    @SerializedName("userId")
    var userId: Int = 0,
    @SerializedName("title")
    var title: String? = null,
    @SerializedName("body")
    var body: String? = null,
    @SerializedName("user")
    val user: User? = null
): Parcelable