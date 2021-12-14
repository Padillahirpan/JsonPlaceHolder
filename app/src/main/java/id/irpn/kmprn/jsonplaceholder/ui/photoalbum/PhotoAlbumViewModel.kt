package id.irpn.kmprn.jsonplaceholder.ui.photoalbum

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.irpn.kmprn.core.data.Resource
import id.irpn.kmprn.core.domain.model.PhotoAlbum
import id.irpn.kmprn.core.domain.usecase.PostUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
 * Created by irpanpadillah on 12/12/21
 * Email: padillahirpan8@gmail.com
 */


class PhotoAlbumViewModel(
    private val postUseCase: PostUseCase
): ViewModel() {

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val _photoAlbum by lazy { MutableLiveData<Resource<List<PhotoAlbum>>>() }
    val photoAlbums: LiveData<Resource<List<PhotoAlbum>>> by lazy { _photoAlbum }

    fun getPhotoAlbum(albumId: Int) {
        uiScope.launch {
            postUseCase.getPhotoAlbum(albumId).collect {
                _photoAlbum.postValue(it)
            }
        }
    }
}