package cz.airbank.airbanktest

import android.app.Application
import cz.airbank.airbanktest.di.dataModule
import cz.airbank.airbanktest.di.uiModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class AirbankTestApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            androidLogger(Level.DEBUG)
            modules(dataModule, uiModule)
        }
    }
}