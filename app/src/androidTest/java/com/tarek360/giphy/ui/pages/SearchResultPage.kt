package com.tarek360.giphy.ui.pages

import com.tarek360.giphy.ui.screens.SearchResultScreen

object SearchResultPage {

    private val screen = SearchResultScreen()

    fun enterSearchQuery(query: String) {
        screen {
            searchTextField {
                typeText(query)
            }
        }
    }

    fun clickImage(position: Int) {
        screen {
            imagesRecyclerView {
                childAt<SearchResultScreen.ImageItem>(position) {
                    isVisible()
                    click()
                }
            }
        }
    }

    fun back() {
        screen {
            pressBack()
        }
    }

}