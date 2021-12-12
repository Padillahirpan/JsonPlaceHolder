package id.irpn.kmprn.core.data

import id.irpn.kmprn.core.data.source.remote.RemoteDataSource
import id.irpn.kmprn.core.data.source.remote.network.ApiResponse
import id.irpn.kmprn.core.domain.model.PhotoAlbum
import id.irpn.kmprn.core.domain.model.PostComment
import id.irpn.kmprn.core.domain.model.Posts
import id.irpn.kmprn.core.domain.model.UserAlbum
import id.irpn.kmprn.core.domain.repository.IPostRepository
import id.irpn.kmprn.core.utils.DataMapper.convertPostWithUser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

/**
 * Created by irpanpadillah on 11/12/21
 * Email: padillahirpan8@gmail.com
 */

class PostRepository(
    private val remoteDataSource: RemoteDataSource
): IPostRepository {
    override fun getPosts(): Flow<Resource<List<Posts>>> {
        return flow {
            this.emit(Resource.Loading())
            when(val result = remoteDataSource.getListPost().first()) {
                is ApiResponse.Success -> {
                    val listResponse = convertPostWithUser(result.data)
                    this.emit(Resource.Success(listResponse))
                }
                is ApiResponse.Error -> {
                    val error: Resource<List<Posts>> = Resource.Error(result.errorMessage)
                    this.emit(error)
                }
                is ApiResponse.Empty -> {
                    this.emit(Resource.Success(mutableListOf<Posts>().toList()))
                }
            }
        }
    }

    override fun getPostComments(postId: Int): Flow<Resource<List<PostComment>>> {
        return flow {
            this.emit(Resource.Loading())
            when(val result = remoteDataSource.getListPostComment(postId).first()) {
                is ApiResponse.Success -> {
                    val listComment = result.data.map {
                        PostComment(
                            id = it.id,
                            postId = it.postId,
                            name = it.name,
                            email = it.email,
                            body = it.body
                        )
                    }
                    this.emit(Resource.Success(listComment))
                }
                is ApiResponse.Error -> {
                    val error: Resource<List<PostComment>> = Resource.Error(result.errorMessage)
                    this.emit(error)
                }
                is ApiResponse.Empty -> {
                    this.emit(Resource.Success(mutableListOf<PostComment>().toList()))
                }
            }
        }
    }

    override fun getUserAlbum(userId: Int): Flow<Resource<List<UserAlbum>>> {
        return flow {
            this.emit(Resource.Loading())
            when(val result = remoteDataSource.getListUserAlbum(userId).first()) {
                is ApiResponse.Success -> {
                    val listUserAlbum = result.data.map {
                        UserAlbum(
                            id = it.id,
                            userId = it.userId,
                            title = it.title
                        )
                    }
                    this.emit(Resource.Success(listUserAlbum))
                }
                is ApiResponse.Error -> {
                    val error: Resource<List<UserAlbum>> = Resource.Error(result.errorMessage)
                    this.emit(error)
                }
                is ApiResponse.Empty -> {
                    this.emit(Resource.Success(mutableListOf<UserAlbum>().toList()))
                }
            }
        }
    }

    override fun getPhotoAlbum(albumId: Int): Flow<Resource<List<PhotoAlbum>>> {
        return flow {
            this.emit(Resource.Loading())
            when(val result = remoteDataSource.getListPhotoAlbum(albumId).first()) {
                is ApiResponse.Success -> {
                    val listPhotoAlbum = result.data.map {
                        PhotoAlbum(
                            albumId = it.albumId,
                            id = it.id,
                            title = it.title,
                            url = it.url,
                            thumbnailUrl = it.thumbnailUrl
                        )
                    }
                    this.emit(Resource.Success(listPhotoAlbum))
                }
                is ApiResponse.Error -> {
                    val error: Resource<List<PhotoAlbum>> = Resource.Error(result.errorMessage)
                    this.emit(error)
                }
                is ApiResponse.Empty -> {
                    this.emit(Resource.Success(mutableListOf<PhotoAlbum>().toList()))
                }
            }
        }
    }
}