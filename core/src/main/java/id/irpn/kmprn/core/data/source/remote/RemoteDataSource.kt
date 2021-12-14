package id.irpn.kmprn.core.data.source.remote

import android.util.Log
import id.irpn.kmprn.core.data.source.remote.network.ApiResponse
import id.irpn.kmprn.core.data.source.remote.network.ApiService
import id.irpn.kmprn.core.data.source.remote.response.PhotoAlbumResponse
import id.irpn.kmprn.core.data.source.remote.response.PostCommentResponse
import id.irpn.kmprn.core.data.source.remote.response.PostResponse
import id.irpn.kmprn.core.data.source.remote.response.UserAlbumResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.lang.Exception

/**
 * Created by irpanpadillah on 11/12/21
 * Email: padillahirpan8@gmail.com
 */

class RemoteDataSource(
    private val apiService: ApiService
) {

    fun getListUsers() {
        flow {
            try {
                val response = apiService.getListUser()
                if (response.isNotEmpty()) {
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    fun getListPost(): Flow<ApiResponse<List<PostResponse>>> {
        return flow {
            try {
                val response = apiService.getListPosts()
                if (response.isNotEmpty()) {
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    fun getListUserAlbum(userId: Int): Flow<ApiResponse<List<UserAlbumResponse>>> {
        return flow {
            try {
                val response = apiService.getUserAlbums(userId)
                if (response.isNotEmpty()) {
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    fun getListPhotoAlbum(albumId: Int): Flow<ApiResponse<List<PhotoAlbumResponse>>> {
        return flow {
            try {
                val response = apiService.getPhotoAlbums(albumId)
                if (response.isNotEmpty()) {
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    fun getListPostComment(postId: Int): Flow<ApiResponse<List<PostCommentResponse>>> {
        return flow {
            try {
                val response = apiService.getPostComments(postId)
                if (response.isNotEmpty()) {
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }
}