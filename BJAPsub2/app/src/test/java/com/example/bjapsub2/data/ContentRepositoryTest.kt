package com.example.bjapsub2.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.bjapsub2.data.data_utils.LiveDataTestUtil
import com.example.bjapsub2.data.data_utils.MockData
import com.example.bjapsub2.data.remote.RemoteDataSource
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.verify
import junit.framework.Assert.assertEquals
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class ContentRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = Mockito.mock(RemoteDataSource::class.java)
    private val moviesRepository = FakeContentRepository(remote)

    private val movieResponses = MockData.generateMockMovies()
    private val movieContentId = movieResponses[0].contentId

    private val tvShowResponses = MockData.generateMockTvShow()
    private val tvContentId = tvShowResponses[0].contentId

    @Test
    fun getAllMovie() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadContentCallback)
                .onAllContentReceived(movieResponses)
            null
        }.`when`(remote).getAllMovies(any())
        val courseEntities = LiveDataTestUtil.getValue(moviesRepository.getAllMovie())
        verify(remote).getAllMovies(any())

        Assert.assertNotNull(courseEntities)
        assertEquals(movieResponses.size.toLong(), courseEntities.size.toLong())
    }

    @Test
    fun getAllTv() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadContentCallback)
                .onAllContentReceived(movieResponses)
            null
        }.`when`(remote).getAllTvShow(any())
        val courseEntities = LiveDataTestUtil.getValue(moviesRepository.getAllTvShow())
        verify(remote).getAllTvShow(any())

        Assert.assertNotNull(courseEntities)
        assertEquals(movieResponses.size.toLong(), courseEntities.size.toLong())
    }

    @Test
    fun getMovieWithModules() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadContentCallback)
                .onAllContentReceived(movieResponses)
            null
        }.`when`(remote).getAllMovies(any())
        val resultContent = LiveDataTestUtil.getValue(moviesRepository.getMovieWithModules(movieContentId))
        verify(remote).getAllMovies(any())
        Assert.assertNotNull(resultContent)
        Assert.assertNotNull(resultContent.title)
        assertEquals(movieResponses[0].title, resultContent.title)
    }

    @Test
    fun getTvShowWithModules() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadContentCallback)
                .onAllContentReceived(tvShowResponses)
            null
        }.`when`(remote).getAllTvShow(any())
        val resultContent = LiveDataTestUtil.getValue(moviesRepository.getTvShowWithModules(tvContentId))
        verify(remote).getAllTvShow(any())
        Assert.assertNotNull(resultContent)
        Assert.assertNotNull(resultContent.title)
        assertEquals(tvShowResponses[0].title, resultContent.title)
    }
}