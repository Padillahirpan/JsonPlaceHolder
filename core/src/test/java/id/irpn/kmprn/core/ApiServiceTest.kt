package id.irpn.kmprn.core

import id.irpn.kmprn.core.data.source.remote.network.ApiService
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by irpanpadillah on 14/12/21
 * Email: padillahirpan8@gmail.com
 */


class ApiServiceTest {
    private lateinit var apiService: ApiService

    @Before
    fun setup() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService = retrofit.create(ApiService::class.java)
    }

    @Test
    fun callGetUsersReturnListUser() {
        runBlocking {
            val users = apiService.getListUser()
            Assert.assertTrue(users.isNotEmpty())
        }
    }

    @Test
    fun callGetPostsReturnListPost() {
        runBlocking {
            val posts = apiService.getListPosts()
            Assert.assertTrue(posts.isNotEmpty())
        }
    }

    @Test
    fun callGetPostCommentReturnListCommentByPost() {
        runBlocking {
            val comments = apiService.getPostComments(1)
            Assert.assertTrue(comments.isNotEmpty())
        }
    }

    @Test
    fun callGetUserAlbumsReturnListAlbum() {
        runBlocking {
            val albums = apiService.getUserAlbums(1)
            Assert.assertTrue(albums.isNotEmpty())
        }
    }

    @Test
    fun callGetPhotoAlbumsReturnPhotoAlbumByUser() {
        runBlocking {
            val albums = apiService.getPhotoAlbums(1)
            Assert.assertTrue(albums.isNotEmpty())
        }
    }
}