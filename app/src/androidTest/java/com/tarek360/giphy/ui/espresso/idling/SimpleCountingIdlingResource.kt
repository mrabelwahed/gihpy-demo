package com.tarek360.giphy.ui.espresso.idling

import androidx.test.espresso.IdlingResource
import java.util.concurrent.atomic.AtomicInteger

class SimpleCountingIdlingResource(private val resourceName: String) : IdlingResource {

    private val counter = AtomicInteger(0)

    private var resourceCallback: IdlingResource.ResourceCallback? = null

    override fun getName(): String = resourceName

    override fun isIdleNow(): Boolean {
        val count = counter.get()
        return count == 0
    }

    override fun registerIdleTransitionCallback(callback: IdlingResource.ResourceCallback?) {
        resourceCallback = callback
    }

    fun increment() = counter.getAndIncrement()

    fun decrement() {
        val counterVal = counter.decrementAndGet()
        when {
            counterVal == 0 -> resourceCallback?.onTransitionToIdle()
            counterVal < 0 -> throw IllegalStateException("Counter has been corrupted!")
        }
    }
}
