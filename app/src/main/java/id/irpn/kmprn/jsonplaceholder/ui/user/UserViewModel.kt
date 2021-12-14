package id.irpn.kmprn.jsonplaceholder.ui.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.irpn.kmprn.core.data.Resource
import id.irpn.kmprn.core.domain.model.UserAlbum
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

class UserViewModel(
    private val postUseCase: PostUseCase
): ViewModel() {

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val _usersAlbum by lazy { MutableLiveData<Resource<List<UserAlbum>>>() }
    val usersAlbums: LiveData<Resource<List<UserAlbum>>> by lazy { _usersAlbum }

    fun getUserAlbum(userId: Int) {
        uiScope.launch {
            postUseCase.getUserAlbum(userId).collect {
                _usersAlbum.postValue(it)
            }
        }
    }
}