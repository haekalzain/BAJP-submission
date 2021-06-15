package com.example.bjapsub2.ui.content

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.bjapsub2.data.ContentEntity
import com.example.bjapsub2.data.MoviesRepository
import com.example.bjapsub2.data.data_utils.MockData
import com.example.bjapsub2.data.viewModel.ContentViewModel
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ContentViewModelTest {
    private lateinit var viewModel: ContentViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var moviesRepository: MoviesRepository

    @Mock
    private lateinit var observer: Observer<List<ContentEntity>>

    @Before
    fun setUp() {
        viewModel = ContentViewModel(moviesRepository)
    }

    @Test
    fun getMovies() {
        val moviesContent = MockData.generateDummyMovies()
        val movies = MutableLiveData<List<ContentEntity>>()
        movies.value = moviesContent

        Mockito.`when`(moviesRepository.getAllMovie()).thenReturn(movies)
        val movieEntities = viewModel.getMovies().value
        Mockito.verify(moviesRepository).getAllMovie()
        Assert.assertNotNull(movieEntities)
        Assert.assertEquals(10, movieEntities?.size)

        viewModel.getMovies().observeForever(observer)
        Mockito.verify(observer).onChanged(moviesContent)
    }

    @Test
    fun getTvShows() {
        val tvShowContent = MockData.generateDummyTvShow()
        val tvShow = MutableLiveData<List<ContentEntity>>()
        tvShow.value = tvShowContent

        Mockito.`when`(moviesRepository.getAllTvShow()).thenReturn(tvShow)
        val tvEntity = viewModel.getTvShow().value
        Mockito.verify(moviesRepository).getAllTvShow()
        Assert.assertNotNull(tvEntity)
        Assert.assertEquals(10, tvEntity?.size)

        viewModel.getTvShow().observeForever(observer)
        Mockito.verify(observer).onChanged(tvShowContent)
    }
}