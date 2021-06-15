package com.example.bjapsub2.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.bjapsub2.data.ContentEntity
import com.example.bjapsub2.data.MoviesRepository
import com.example.bjapsub2.data.data_utils.MockData
import com.example.bjapsub2.presentation.ui.detail.DetailContentViewModel
import junit.framework.Assert.assertEquals
import org.junit.Assert
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
    private val dummyMovie = MockData.generateDummyMovies()[0]
    private val movieId = dummyMovie.contentId

    private val dummyTvShow = MockData.generateDummyTvShow()[0]
    private val tvShowId = dummyTvShow.contentId

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var moviesRepository: MoviesRepository

    @Mock
    private lateinit var courseObserver: Observer<ContentEntity>

    @Before
    fun setUp() {
        viewModel = DetailContentViewModel(moviesRepository)
        viewModel.setSelectedMovieContent(movieId)
        viewModel.setSelectedTvShowContent(tvShowId)
    }

    @Test
    fun getMovies() {
        val movie = MutableLiveData<ContentEntity>()
        movie.value = dummyMovie

        Mockito.`when`(moviesRepository.getMovieWithModules(movieId)).thenReturn(movie)
        val movieEntity = viewModel.getMovie().value as ContentEntity
        Mockito.verify(moviesRepository).getMovieWithModules(movieId)
        Assert.assertNotNull(movieEntity)
        assertEquals(dummyMovie.contentId, movieEntity.contentId)
        assertEquals(dummyMovie.title, movieEntity.title)
        assertEquals(dummyMovie.releasedDate, movieEntity.releasedDate)
        assertEquals(dummyMovie.sinopsis, movieEntity.sinopsis)
        assertEquals(dummyMovie.genre, movieEntity.genre)
        assertEquals(dummyMovie.duration, movieEntity.duration)
        assertEquals(dummyMovie.imagePath, movieEntity.imagePath)


        viewModel.getMovie().observeForever(courseObserver)
        Mockito.verify(courseObserver).onChanged(dummyMovie)
    }


    @Test
    fun getTvShow() {
        val tvShow = MutableLiveData<ContentEntity>()
        tvShow.value = dummyTvShow

        Mockito.`when`(moviesRepository.getTvShowWithModules(tvShowId)).thenReturn(tvShow)
        val tvShowEntity = viewModel.getTvShow().value as ContentEntity
        Mockito.verify(moviesRepository).getTvShowWithModules(tvShowId)
        Assert.assertNotNull(tvShowEntity)
        assertEquals(dummyTvShow.contentId, tvShowEntity.contentId)
        assertEquals(dummyTvShow.title, tvShowEntity.title)
        assertEquals(dummyTvShow.releasedDate, tvShowEntity.releasedDate)
        assertEquals(dummyTvShow.sinopsis, tvShowEntity.sinopsis)
        assertEquals(dummyTvShow.genre, tvShowEntity.genre)
        assertEquals(dummyTvShow.duration, tvShowEntity.duration)
        assertEquals(dummyTvShow.imagePath, tvShowEntity.imagePath)


        viewModel.getTvShow().observeForever(courseObserver)
        Mockito.verify(courseObserver).onChanged(dummyTvShow)
    }

}