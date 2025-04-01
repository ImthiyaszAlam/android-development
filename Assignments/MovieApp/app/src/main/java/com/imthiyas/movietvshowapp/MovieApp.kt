package com.imthiyas.movietvshowapp

import android.app.Application
import appModule
import org.koin.core.context.GlobalContext.startKoin

class MovieApp:Application() {
    override fun onCreate() {
        super.onCreate()

        // Start Koin Dependency Injection
        startKoin {
            modules(appModule)
        }
    }
}