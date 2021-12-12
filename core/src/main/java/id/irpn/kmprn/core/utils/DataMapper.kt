package id.irpn.kmprn.core.utils

import id.irpn.kmprn.core.data.source.remote.response.PostResponse
import id.irpn.kmprn.core.domain.model.Posts

/**
 * Created by irpanpadillah on 11/12/21
 * Email: padillahirpan8@gmail.com
 */
object DataMapper {
    fun convertPostWithUser(post: List<PostResponse>): List<Posts> {
        return post.map {
            Posts(
                id = it.id,
                userId = it.userId,
                title = it.title,
                body = it.body,
                name = "",
                userCompany = ""
            )
        }
    }
}