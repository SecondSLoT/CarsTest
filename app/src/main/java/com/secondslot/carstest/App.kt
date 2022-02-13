package com.secondslot.carstest

import android.app.Application
import com.secondslot.carstest.di.AppComponent
import com.secondslot.carstest.di.DaggerAppComponent

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.factory().create(applicationContext)
    }

    companion object {
        lateinit var appComponent: AppComponent
            private set
    }
}
