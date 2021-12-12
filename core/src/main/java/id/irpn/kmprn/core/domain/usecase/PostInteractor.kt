package id.irpn.kmprn.core.domain.usecase

import id.irpn.kmprn.core.data.Resource
import id.irpn.kmprn.core.domain.model.PhotoAlbum
import id.irpn.kmprn.core.domain.model.PostComment
import id.irpn.kmprn.core.domain.model.Posts
import id.irpn.kmprn.core.domain.model.UserAlbum
import id.irpn.kmprn.core.domain.repository.IPostRepository
import kotlinx.coroutines.flow.Flow

/**
 * Created by irpanpadillah on 11/12/21
 * Email: padillahirpan8@gmail.com
 */

class PostInteractor(
    private val postRepository: IPostRepository
): PostUseCase {
    override fun getPosts(): Flow<Resource<List<Posts>>> {
        return postRepository.getPosts()
    }

    override fun getPostComments(postId: Int): Flow<Resource<List<PostComment>>> {
        return postRepository.getPostComments(postId)
    }

    override fun getUserAlbum(userId: Int): Flow<Resource<List<UserAlbum>>> {
        return postRepository.getUserAlbum(userId)
    }

    override fun getPhotoAlbum(albumId: Int): Flow<Resource<List<PhotoAlbum>>> {
        return postRepository.getPhotoAlbum(albumId)
    }
}