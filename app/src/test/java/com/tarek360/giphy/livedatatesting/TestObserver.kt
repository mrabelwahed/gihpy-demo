package com.tarek360.giphy.livedatatesting

import androidx.lifecycle.LiveData

fun <T> LiveData<T>.test(): TestObserver<T> {
    return TestObserver.test(this)
}
