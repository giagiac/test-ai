package it.puntoettore.myapplication

import android.app.Application
import it.puntoettore.myapplication.di.commonModule
import it.puntoettore.myapplication.di.platformModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApplication)
            modules(commonModule, platformModule)
        }
    }
}
