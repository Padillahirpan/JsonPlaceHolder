package id.irpn.kmprn.core.utils

import id.irpn.kmprn.core.data.source.remote.response.*
import id.irpn.kmprn.core.domain.model.*

/**
 * Created by irpanpadillah on 11/12/21
 * Email: padillahirpan8@gmail.com
 */
object DataMapper {

    fun convertUser(postResponse: UserResponse): User {
        return postResponse.let {
            User(
                id = it.id,
                name = it.name,
                username = it.username,
                email = it.email,
                phone = it.phone,
                website = it.website,
                street = it.address?.street,
                suite = it.address?.suite,
                city = it.address?.city,
                zipCode = it.address?.zipCode,
                companyName = it.company?.name,
                companyCatchPhrase = it.company?.catchPhrase
            )
        }
    }

    fun convertPostWithUser(post: List<PostResponse>, listUser: List<UserResponse>): List<Posts> {
        return post.map {
            var mUser: User? = null
            for (user in listUser) {
                if (it.userId == user.id) {
                    mUser = convertUser(user)
                    break
                }
            }

            Posts(
                id = it.id,
                userId = it.userId,
                title = it.title,
                body = it.body,
                user = mUser,
            )
        }
    }

    fun convertPostComments(comments: List<PostCommentResponse>): List<PostComment> {
        return comments.map {
            PostComment(
                id = it.id,
                postId = it.postId,
                name = it.name,
                email = it.email,
                body = it.body
            )
        }
    }

    fun convertUserAlbum(albums: List<UserAlbumResponse>): List<UserAlbum> {
        return albums.map {
            UserAlbum(
                id = it.id,
                userId = it.userId,
                title = it.title
            )
        }
    }

    fun convertPhotoAlbum(photos: List<PhotoAlbumResponse>): List<PhotoAlbum> {
        return photos.map {
            PhotoAlbum(
                albumId = it.albumId,
                id = it.id,
                title = it.title,
                url = it.url,
                thumbnailUrl = it.thumbnailUrl
            )
        }
    }
}