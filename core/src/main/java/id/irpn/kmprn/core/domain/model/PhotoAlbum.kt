package id.irpn.kmprn.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


/**
 * Created by irpanpadillah on 11/12/21
 * Email: padillahirpan8@gmail.com
 */


@Parcelize
data class PhotoAlbum(
    val albumId: Int,
    val id: Int,
    val title: String? = null,
    val url: String? = null,
    val thumbnailUrl: String? = null
): Parcelable