package com.tarek360.giphy.ui

import android.app.Application
import android.content.Context
import androidx.test.espresso.IdlingRegistry
import androidx.test.runner.AndroidJUnitRunner
import com.tarek360.giphy.ui.espresso.idling.EspressoIdlingResource

open class UiRunner : AndroidJUnitRunner() {

    override fun callApplicationOnCreate(app: Application?) {
        super.callApplicationOnCreate(app)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getIdlingResource())
    }

    override fun onDestroy() {
        super.onDestroy()
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getIdlingResource())
    }

    override fun newApplication(
        cl: ClassLoader?,
        className: String?,
        context: Context?
    ): Application {
        return super.newApplication(cl, TestApp::class.java.name, context)
    }
}
