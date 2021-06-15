package com.example.bjapsub1

import com.example.bjapsub1.presentation.data.MockData
import com.example.bjapsub1.presentation.ui.detail.DetailContentViewModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class DetailContentViewModelTest {
    private lateinit var contentViewModel: DetailContentViewModel
    private val dummyMovieContent = MockData.generateMockMovies()[0]
    private val dummyTvContent = MockData.generateMockTvShow()[0]
    private val movieContentId = dummyMovieContent.contentId
    private val tvShowContentId = dummyTvContent.contentId

    @Before
    fun setUp() {
        contentViewModel = DetailContentViewModel()
        contentViewModel.setSelectedMovieContent(movieContentId)
        contentViewModel.setSelectedTvShowContent(tvShowContentId)

    }

    @Test
    fun getContent() {
        val movieEntity = contentViewModel.getMovies()
        val tvShowEntity = contentViewModel.getTvShow()

        assertNotNull(movieEntity)
        assertNotNull(tvShowEntity)

        assertEquals(dummyMovieContent.contentId, movieEntity.contentId)
        assertEquals(dummyMovieContent.releasedDate, movieEntity.releasedDate)
        assertEquals(dummyMovieContent.sinopsis, movieEntity.sinopsis)
        assertEquals(dummyMovieContent.genre, movieEntity.genre)
        assertEquals(dummyMovieContent.duration, movieEntity.duration)
        assertEquals(dummyMovieContent.imagePath, movieEntity.imagePath)
        assertEquals(dummyMovieContent.title, movieEntity.title)

        assertEquals(dummyTvContent.contentId, tvShowEntity.contentId)
        assertEquals(dummyTvContent.releasedDate, tvShowEntity.releasedDate)
        assertEquals(dummyTvContent.sinopsis, tvShowEntity.sinopsis)
        assertEquals(dummyTvContent.genre, tvShowEntity.genre)
        assertEquals(dummyTvContent.duration, tvShowEntity.duration)
        assertEquals(dummyTvContent.imagePath, tvShowEntity.imagePath)
        assertEquals(dummyTvContent.title, tvShowEntity.title)
    }
}