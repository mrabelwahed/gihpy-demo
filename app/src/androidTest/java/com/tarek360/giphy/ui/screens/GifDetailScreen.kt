package com.tarek360.giphy.ui.screens

import com.agoda.kakao.image.KImageView
import com.agoda.kakao.screen.Screen
import com.tarek360.giphy.R

open class GifDetailScreen : Screen<GifDetailScreen>() {
    val gifImageView = KImageView{ withId(R.id.gifImageView) }
}
