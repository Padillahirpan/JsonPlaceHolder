package id.irpn.kmprn.core.domain.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

/**
 * Created by irpanpadillah on 14/12/21
 * Email: padillahirpan8@gmail.com
 */

@Parcelize
data class User(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("username")
    val username: String? = null,
    @SerializedName("email")
    var email: String? = null,
    @SerializedName("phone")
    var phone: String? = null,
    @SerializedName("website")
    var website: String? = null,
    @SerializedName("street")
    var street: String? = null,
    @SerializedName("suite")
    var suite: String? = null,
    @SerializedName("city")
    var city: String? = null,
    @SerializedName("zipcode")
    var zipCode: String? = null,
    @SerializedName("companyName")
    var companyName: String? = null,
    @SerializedName("companyCatchPhrase")
    var companyCatchPhrase: String? = null,
): Parcelable