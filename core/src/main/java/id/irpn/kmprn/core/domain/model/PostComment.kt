package id.irpn.kmprn.core.domain.model


/**
 * Created by irpanpadillah on 11/12/21
 * Email: padillahirpan8@gmail.com
 */

data class PostComment(
    var id: Int = 0,
    var postId: Int = 0,
    var name: String? = null,
    var email: String? = null,
    var body: String? = null
)