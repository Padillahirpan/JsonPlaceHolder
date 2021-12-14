package id.irpn.kmprn.jsonplaceholder.ui.post

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import id.irpn.kmprn.core.data.Resource
import id.irpn.kmprn.core.domain.model.PostComment
import id.irpn.kmprn.core.domain.usecase.PostUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
 * Created by irpanpadillah on 11/12/21
 * Email: padillahirpan8@gmail.com
 */

class PostViewModel(
    private val postUseCase: PostUseCase
): ViewModel() {

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val _comments by lazy { MutableLiveData<Resource<List<PostComment>>>() }
    val comments: LiveData<Resource<List<PostComment>>> by lazy { _comments }

    val posts = postUseCase.getPosts().asLiveData()

    fun getPostComment(postId: Int) {
        uiScope.launch {
            postUseCase.getPostComments(postId).collect {
                _comments.postValue(it)
            }
        }
    }
}