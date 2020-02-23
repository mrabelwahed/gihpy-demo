package com.tarek360.giphy.concurrency

sealed class JobResult<T> {
    class Success<T>(val value: T) : JobResult<T>()
    class Failure<T>(val throwable: Throwable) : JobResult<T>()
}
