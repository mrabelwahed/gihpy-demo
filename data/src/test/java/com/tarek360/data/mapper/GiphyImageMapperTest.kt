package com.tarek360.data.mapper

import com.tarek360.data.model.GiphyImage
import com.tarek360.data.model.GiphyImageData
import com.tarek360.data.model.GiphySizes
import com.tarek360.test.shouldEqual
import org.junit.Test

class GiphyImageMapperTest {

    private val mapper by lazy { GiphyImageMapper() }

    @Test
    fun map() {

        // Arrange
        val input = listOf(
            GiphyImageData(
                GiphySizes(
                    GiphyImage("normalFramesUrl1"),
                    GiphyImage("lowFrames1")
                )
            ),
            GiphyImageData(
                GiphySizes(
                    GiphyImage("normalFramesUrl2"),
                    GiphyImage("lowFrames2")
                )
            )
        )

        // Act
        val output = mapper.map(input)

        // Assert
        output.size shouldEqual 2
        output.first().normalFramesUrl shouldEqual "normalFramesUrl1"
        output.first().lowFramesUrl shouldEqual "lowFrames1"
        output.last().normalFramesUrl shouldEqual "normalFramesUrl2"
        output.last().lowFramesUrl shouldEqual "lowFrames2"
    }

    @Test
    fun mapNotNull() {

        // Arrange
        val input = listOf(
            GiphyImageData(null),
            GiphyImageData(
                GiphySizes(
                    GiphyImage("normalFramesUrl2"),
                    GiphyImage("lowFrames2")
                )
            )
        )

        // Act
        val output = mapper.map(input)

        // Assert
        output.size shouldEqual 1
        output.first().normalFramesUrl shouldEqual "normalFramesUrl2"
        output.first().lowFramesUrl shouldEqual "lowFrames2"
    }
}
