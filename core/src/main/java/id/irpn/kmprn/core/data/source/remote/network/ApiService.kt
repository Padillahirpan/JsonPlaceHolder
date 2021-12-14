package id.irpn.kmprn.core.data.source.remote.network

import id.irpn.kmprn.core.data.source.remote.response.*
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by irpanpadillah on 11/12/21
 * Email: padillahirpan8@gmail.com
 */


interface ApiService {
    @GET("posts?_limit=2")
    suspend fun getListPosts(): List<PostResponse>

    @GET("posts/{post_id}/comments")
    suspend fun getPostComments(
        @Path("post_id") postId: Int
    ): List<PostCommentResponse>

    @GET("users")
    suspend fun getListUser(): List<UserResponse>

    @GET("users/{user_id}/albums")
    suspend fun getUserAlbums(
        @Path("user_id") userId: Int
    ): List<UserAlbumResponse>

    @GET("albums/{album_id}/photos")
    suspend fun getPhotoAlbums(
        @Path("album_id") albumId: Int
    ): List<PhotoAlbumResponse>
}