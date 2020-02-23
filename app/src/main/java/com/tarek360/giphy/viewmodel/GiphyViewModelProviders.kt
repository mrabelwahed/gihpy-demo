package com.tarek360.giphy.viewmodel

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider

interface GiphyViewModelProviders {
    fun of(activity: FragmentActivity): ViewModelProvider
    fun of(fragment: Fragment): ViewModelProvider
}
