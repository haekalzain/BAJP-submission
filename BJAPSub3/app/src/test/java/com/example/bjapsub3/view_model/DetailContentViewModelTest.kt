package com.example.bjapsub3.view_model

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.bjapsub3.data.ContentRepository
import com.example.bjapsub3.data.source.local.entity.ContentEntity
import com.example.bjapsub3.data.view_model.DetailContentViewModel
import com.example.bjapsub3.util.MockData
import com.example.bjapsub3.vo.Resource
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailContentViewModelTest {
    private lateinit var viewModel: DetailContentViewModel
    private val dummyMovies = MockData.generateMockMovies()[0]
    private val movieContentId = dummyMovies.contentId

    private val dummyTvShows = MockData.generateMockTvShow()[0]
    private val tvShowContentId = dummyTvShows.contentId

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var moviesRepository: ContentRepository

    @Mock
    private lateinit var observer: Observer<Resource<ContentEntity>>

    @Before
    fun setUp() {
        viewModel = DetailContentViewModel(moviesRepository)
        viewModel.setSelectedMovieContent(movieContentId)
        viewModel.setSelectedTvShowContent(tvShowContentId)
    }

    @Test
    fun `setSelectedMovie should be success`() {
        val expected = MutableLiveData<Resource<ContentEntity>>()
        expected.value = Resource.success(dummyMovies)

        Mockito.`when`(moviesRepository.getContentWithIdAndType(movieContentId,"movies")).thenReturn(expected)

        viewModel.getMovie.observeForever(observer)

        Mockito.verify(observer).onChanged(expected.value)

        val expectedValue = expected.value
        val actualValue = viewModel.getMovie.value

        assertEquals(expectedValue, actualValue)
    }

    @Test
    fun `setFavoriteMovie should be success trigger contentModule observer`() {
        val expected = MutableLiveData<Resource<ContentEntity>>()
        expected.value = Resource.success(dummyMovies)

        Mockito.`when`(moviesRepository.getContentWithIdAndType(movieContentId,"movies")).thenReturn(expected)

        viewModel.setFavorite("movies")
        viewModel.getMovie.observeForever(observer)

        Mockito.verify(observer).onChanged(expected.value)

        val expectedValue = expected.value
        val actualValue = viewModel.getMovie.value

        assertEquals(expectedValue, actualValue)
    }


    @Test
    fun `setSelectedTvShow should be success`() {
        val expected = MutableLiveData<Resource<ContentEntity>>()
        expected.value = Resource.success(dummyTvShows)

        Mockito.`when`(moviesRepository.getContentWithIdAndType(tvShowContentId,"tvShow")).thenReturn(expected)

        viewModel.getTvShow.observeForever(observer)

        Mockito.verify(observer).onChanged(expected.value)

        val expectedValue = expected.value
        val actualValue = viewModel.getTvShow.value

        assertEquals(expectedValue, actualValue)
    }

    @Test
    fun `setFavoriteTvShow should be success trigger contentModule observer`() {
        val expected = MutableLiveData<Resource<ContentEntity>>()
        expected.value = Resource.success(dummyTvShows)

        Mockito.`when`(moviesRepository.getContentWithIdAndType(tvShowContentId,"tvShow")).thenReturn(expected)

        viewModel.setFavorite("tvShow")
        viewModel.getTvShow.observeForever(observer)

        Mockito.verify(observer).onChanged(expected.value)

        val expectedValue = expected.value
        val actualValue = viewModel.getTvShow.value

        assertEquals(expectedValue, actualValue)
    }

}