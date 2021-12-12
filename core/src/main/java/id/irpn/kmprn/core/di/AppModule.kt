package id.irpn.kmprn.core.di

import id.irpn.kmprn.core.domain.usecase.PostInteractor
import id.irpn.kmprn.core.domain.usecase.PostUseCase
import org.koin.dsl.module

/**
 * Created by irpanpadillah on 11/12/21
 * Email: padillahirpan8@gmail.com
 */

val useCaseModule = module {
    factory<PostUseCase> { PostInteractor(get()) }
}