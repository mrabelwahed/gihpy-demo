package com.tarek360.giphy.ui.dagger

import android.app.Application
import com.tarek360.giphy.dagger.AppModule
import com.tarek360.giphy.dispatcher.DispatcherProvider
import com.tarek360.giphy.ui.espresso.idling.CoroutineDispatcherWrapperIdlingResources
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.InternalCoroutinesApi

@Module
class TestAppModule(app: Application) : AppModule(app) {

    @InternalCoroutinesApi
    @Provides
    override fun provideDispatcherProvider(): DispatcherProvider {
        return object : DispatcherProvider {
            override fun io(): CoroutineDispatcher {
                return CoroutineDispatcherWrapperIdlingResources(
                    super.io()
                )
            }

            override fun main(): CoroutineDispatcher {
                return CoroutineDispatcherWrapperIdlingResources(
                    super.main()
                )
            }
        }
    }

}
