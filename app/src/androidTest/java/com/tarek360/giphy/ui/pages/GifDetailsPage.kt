package com.tarek360.giphy.ui.pages

import com.tarek360.giphy.ui.screens.GifDetailScreen

object GifDetailsPage {

    private val screen = GifDetailScreen()

    fun expectGifImageViewDisplayed() {
        screen {
            gifImageView {
                isDisplayed()
            }
        }
    }

    fun back() {
        screen {
            pressBack()
        }
    }

}