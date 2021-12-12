package id.irpn.kmprn.jsonplaceholder.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import id.irpn.kmprn.core.domain.usecase.PostUseCase

/**
 * Created by irpanpadillah on 11/12/21
 * Email: padillahirpan8@gmail.com
 */

class MainViewModel(
    private val postUseCase: PostUseCase
): ViewModel() {

    val posts = postUseCase.getPosts().asLiveData()
}