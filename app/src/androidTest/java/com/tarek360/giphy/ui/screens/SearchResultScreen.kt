package com.tarek360.giphy.ui.screens

import android.view.View
import com.agoda.kakao.edit.KEditText
import com.agoda.kakao.image.KImageView
import com.agoda.kakao.recycler.KRecyclerItem
import com.agoda.kakao.recycler.KRecyclerView
import com.agoda.kakao.screen.Screen
import com.tarek360.giphy.R
import org.hamcrest.Matcher

open class SearchResultScreen : Screen<SearchResultScreen>() {

    val searchTextField = KEditText { withId(R.id.searchTextField) }

    val imagesRecyclerView: KRecyclerView = KRecyclerView({
        withId(R.id.imagesRecyclerView)
    }, itemTypeBuilder = {
        itemType(SearchResultScreen::ImageItem)
    })

    class ImageItem(parent: Matcher<View>) : KRecyclerItem<ImageItem>(parent) {
        val gifImageView: KImageView = KImageView(parent) { withId(R.id.gifImageView) }
    }

    fun idle(duration: Long) = apply {
        Screen.idle(duration)
    }
}

