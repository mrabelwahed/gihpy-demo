package com.tarek360.giphy.ui

import com.tarek360.giphy.App
import com.tarek360.giphy.dagger.AppComponent
import com.tarek360.giphy.dagger.DaggerAppComponent
import com.tarek360.giphy.ui.dagger.TestAppModule

class TestApp : App() {

    override fun getApplicationComponent(): AppComponent =
        DaggerAppComponent.builder()
            .appModule(TestAppModule(this))
            .build()
}
