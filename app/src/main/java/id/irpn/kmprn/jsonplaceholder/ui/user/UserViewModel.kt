package id.irpn.kmprn.jsonplaceholder.ui.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import id.irpn.kmprn.core.domain.usecase.PostUseCase

/**
 * Created by irpanpadillah on 11/12/21
 * Email: padillahirpan8@gmail.com
 */

class UserViewModel(
    private val postUseCase: PostUseCase
): ViewModel() {

    val userAlbums = postUseCase.getUserAlbum(1).asLiveData()
}