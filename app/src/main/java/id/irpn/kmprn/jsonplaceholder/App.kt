package id.irpn.kmprn.jsonplaceholder

import android.app.Application
import id.irpn.kmprn.core.di.networkModule
import id.irpn.kmprn.core.di.repositoryModule
import id.irpn.kmprn.core.di.useCaseModule
import id.irpn.kmprn.jsonplaceholder.di.DataModule.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

/**
 * Created by irpanpadillah on 11/12/21
 * Email: padillahirpan8@gmail.com
 */

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@App)
            modules(listOf(
                networkModule,
                repositoryModule,
                useCaseModule,
                viewModelModule,
            ))
        }
    }
}