package id.irpn.kmprn.jsonplaceholder.di


import id.irpn.kmprn.jsonplaceholder.ui.main.MainViewModel
import id.irpn.kmprn.jsonplaceholder.ui.post.PostViewModel
import id.irpn.kmprn.jsonplaceholder.ui.user.UserViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by irpanpadillah on 11/12/21
 * Email: padillahirpan8@gmail.com
 */

object DataModule {
    val viewModelModule = module {
        viewModel { MainViewModel(get()) }
        viewModel { PostViewModel(get()) }
        viewModel { UserViewModel(get()) }
    }
}