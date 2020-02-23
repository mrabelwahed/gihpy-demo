package com.tarek360.giphy

import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.bumptech.glide.request.RequestListener

typealias GifDrawableTarget = com.bumptech.glide.request.target.Target<GifDrawable>
typealias GlideTarget<T> = com.bumptech.glide.request.target.Target<T>

//class GlideListener : RequestListener<GifDrawable> {
//    override fun onLoadFailed(
//        e: GlideException?,
//        model: Any?,
//        target: GifDrawableTarget?,
//        isFirstResource: Boolean
//    ): Boolean {
//        return false
//    }
//
//    override fun onResourceReady(
//        resource: GifDrawable?,
//        model: Any?,
//        target: GifDrawableTarget?,
//        dataSource: DataSource?,
//        isFirstResource: Boolean
//    ): Boolean {
//        return false
//    }
//}

class GlideListener<T>(
    private val onFailed: (() -> Unit)? = null,
    private val onReady: (() -> Unit)? = null
) : RequestListener<T> {
    override fun onLoadFailed(
        e: GlideException?, model: Any?, target: GlideTarget<T>?, isFirstResource: Boolean
    ): Boolean {
        onFailed?.invoke()
        return false
    }

    override fun onResourceReady(
        resource: T?,
        model: Any?,
        target: GlideTarget<T>?,
        dataSource: DataSource?,
        isFirstResource: Boolean
    ): Boolean {
        onReady?.invoke()
        return false
    }
}


fun <T> RequestBuilder<T>.onLoadFailed(callback: () -> Unit): RequestBuilder<T> {
    return addListener(GlideListener<T>(onFailed = callback))
}

fun <T> RequestBuilder<T>.onResourceReady(callback: () -> Unit): RequestBuilder<T> {
    return addListener(GlideListener<T>(onReady = callback))
}