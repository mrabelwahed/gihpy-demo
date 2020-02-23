package com.tarek360.giphy.dagger

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.tarek360.data.network.GiphyApi
import com.tarek360.giphy.BuildConfig
import com.tarek360.giphy.dispatcher.DispatcherProvider
import com.tarek360.giphy.viewmodel.GiphyViewModelProviders
import com.tarek360.giphy.viewmodel.GiphyViewModelProvidersImpl
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
open class AppModule(private val app: Application) {
    @Provides
    @Singleton
    fun provideContext(): Context = app

    @Provides
    fun provideGiphyViewModelProviders(factory: ViewModelProvider.Factory): GiphyViewModelProviders {
        return GiphyViewModelProvidersImpl(factory)
    }

    @Provides
    open fun provideDispatcherProvider(): DispatcherProvider {
        return object : DispatcherProvider {}
    }

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {

        val okHttpBuilder = OkHttpClient.Builder()

        if (BuildConfig.DEBUG) {
            okHttpBuilder.addInterceptor(HttpLoggingInterceptor().apply {
                setLevel(HttpLoggingInterceptor.Level.BASIC)
            })
        }

        val okHttpClient: OkHttpClient = okHttpBuilder.build()

        return Retrofit.Builder()
            .baseUrl(BuildConfig.GIPHY_API_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideGiphyApi(retrofit: Retrofit): GiphyApi {
        return retrofit.create(GiphyApi::class.java)
    }
}
