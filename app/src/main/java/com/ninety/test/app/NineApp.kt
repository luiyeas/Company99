package com.ninety.test.app

import android.app.Application
import com.ninety.test.di.*
import org.koin.android.ext.android.startKoin

class NineApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(
            this, listOf(
                mainModule,
                interactorModule,
                compositeModule,
                networkModule,
                repositoryModule,
                navigatorModule
            )
        )
    }
}