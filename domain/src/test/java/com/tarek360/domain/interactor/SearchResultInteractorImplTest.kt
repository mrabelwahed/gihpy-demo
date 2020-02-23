package com.tarek360.domain.interactor

import com.nhaarman.mockitokotlin2.whenever
import com.tarek360.domain.model.Image
import com.tarek360.domain.repo.ImagesRepository
import com.tarek360.test.shouldEqual
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SearchResultInteractorImplTest {

    @Mock
    lateinit var imagesRepository: ImagesRepository

    private val interactor by lazy { SearchResultInteractorImpl(imagesRepository) }


    @Test
    fun searchImages() {

        // Arrange
        val images = listOf(
            Image("lowLink1", "normalLink1"),
            Image("lowLink2", "normalLink2")
        )
        whenever(imagesRepository.searchImages("key", 1, 20)).thenReturn(images)

        // Act
        val output = interactor.searchImages("key", 1, 20)

        // Assert
        output shouldEqual images
        output.size shouldEqual 2
    }
}
