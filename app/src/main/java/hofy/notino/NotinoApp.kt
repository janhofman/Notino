package hofy.notino

import android.app.Application
import hofy.notino.data.di.dataModule
import hofy.notino.domain.di.domainModule
import hofy.notino.presentation.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class NotinoApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@NotinoApp)
            modules(
                viewModelModule,
                dataModule,
                domainModule
            )
        }
    }
}