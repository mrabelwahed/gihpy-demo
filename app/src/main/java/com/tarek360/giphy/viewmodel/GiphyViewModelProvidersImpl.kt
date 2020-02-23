package com.tarek360.giphy.viewmodel

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider

class GiphyViewModelProvidersImpl(private val factory: ViewModelProvider.Factory) :
    GiphyViewModelProviders {
    override fun of(fragment: Fragment): ViewModelProvider {
        return ViewModelProvider(fragment, factory)
    }

    override fun of(activity: FragmentActivity): ViewModelProvider {
        return ViewModelProvider(activity, factory)
    }
}
