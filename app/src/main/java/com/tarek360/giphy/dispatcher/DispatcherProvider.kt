package com.tarek360.giphy.dispatcher

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

interface DispatcherProvider {
    fun io(): CoroutineDispatcher = Dispatchers.IO
    fun main(): CoroutineDispatcher = Dispatchers.Main
}