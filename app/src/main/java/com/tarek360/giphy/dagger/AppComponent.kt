package com.tarek360.giphy.dagger

import com.tarek360.giphy.App
import com.tarek360.giphy.ui.search.SearchResultFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        SearchResultModule::class,
        ViewModelModule::class]
)
interface AppComponent {
    fun inject(target: App)
    fun inject(target: SearchResultFragment)
}
