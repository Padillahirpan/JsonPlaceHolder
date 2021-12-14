package id.irpn.kmprn.core.domain.usecase

import androidx.lifecycle.LiveData
import id.irpn.kmprn.core.data.Resource
import id.irpn.kmprn.core.domain.model.PhotoAlbum
import id.irpn.kmprn.core.domain.model.PostComment
import id.irpn.kmprn.core.domain.model.Posts
import id.irpn.kmprn.core.domain.model.UserAlbum
import kotlinx.coroutines.flow.Flow

/**
 * Created by irpanpadillah on 11/12/21
 * Email: padillahirpan8@gmail.com
 */

interface PostUseCase {
    fun getPosts(): Flow<Resource<List<Posts>>>
    fun getPostComments(postId: Int): Flow<Resource<List<PostComment>>>
    fun getUserAlbum(userId: Int): Flow<Resource<List<UserAlbum>>>
    fun getPhotoAlbum(albumId: Int): Flow<Resource<List<PhotoAlbum>>>
}