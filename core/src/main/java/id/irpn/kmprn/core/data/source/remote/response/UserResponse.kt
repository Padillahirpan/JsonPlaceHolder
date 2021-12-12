package id.irpn.kmprn.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

/**
 * Created by irpanpadillah on 11/12/21
 * Email: padillahirpan8@gmail.com
 */

data class UserResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("email")
    var email: String? = null,
    @SerializedName("address")
    var address: Address? = null,
    @SerializedName("phone")
    var phone: String? = null,
    @SerializedName("website")
    var website: String? = null,
    @SerializedName("company")
    var company: Company? = null

)

data class Address(
    @SerializedName("street")
    var street: String? = null,
    @SerializedName("suite")
    var suite: String? = null,
    @SerializedName("city")
    var city: String? = null,
    @SerializedName("zipcode")
    var zipCode: String? = null,
    @SerializedName("geo")
    var geo: Geo? = null,
)

data class Geo(
    @SerializedName("lat")
    var lat: String? = null,
    @SerializedName("lng")
    var lng: String? = null
)

data class Company(
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("catchPhrase")
    var catchPhrase: String? = null,
    @SerializedName("bs")
    var bs: String? = null
)