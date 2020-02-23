package com.tarek360.giphy

import android.app.Application
import com.tarek360.giphy.dagger.AppComponent
import com.tarek360.giphy.dagger.AppModule
import com.tarek360.giphy.dagger.DaggerAppComponent

open class App : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = getApplicationComponent()
        appComponent.inject(this)
    }

    open fun getApplicationComponent(): AppComponent =
        DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
}
