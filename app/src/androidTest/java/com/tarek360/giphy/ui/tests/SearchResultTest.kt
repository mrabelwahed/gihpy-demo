package com.tarek360.giphy.ui.tests

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.tarek360.giphy.ui.pages.GifDetailsPage
import com.tarek360.giphy.ui.pages.SearchResultPage
import com.tarek360.giphy.ui.GiphyActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SearchResultTest {

    @Rule
    @JvmField
    var activityTestRule = ActivityTestRule(GiphyActivity::class.java)

    @Test
    fun searchResultTest() {

        SearchResultPage.run {
            enterSearchQuery("puppy")
            clickImage(0)
        }

        GifDetailsPage.run {
            expectGifImageViewDisplayed()
            back()
        }

        SearchResultPage.run {
            clickImage(1)
        }

        GifDetailsPage.run {
            expectGifImageViewDisplayed()
            back()
        }
    }
}
