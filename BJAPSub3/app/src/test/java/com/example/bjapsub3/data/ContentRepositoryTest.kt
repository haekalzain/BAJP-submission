package com.example.bjapsub3.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.bjapsub3.data.source.local.LocalDataSource
import com.example.bjapsub3.data.source.local.entity.ContentEntity
import com.example.bjapsub3.data.source.remote.RemoteDataSource
import com.example.bjapsub3.util.AppExecutors
import com.example.bjapsub3.util.MockData
import com.example.bjapsub3.utils.LiveDataUtils
import com.example.bjapsub3.utils.PagedListUtil
import com.example.bjapsub3.vo.Resource
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
    private val local = Mockito.mock(LocalDataSource::class.java)
    private val appExecutors = Mockito.mock(AppExecutors::class.java)


    private val contentRepository = FakeContentRepository(remote, local, appExecutors)

    private val movieContentResponse = MockData.generateRemoteMockMovies()
    private val movieContentId = movieContentResponse[0].id

    private val tvShowContentResponse = MockData.generateRemoteTvShow()
    private val tvShowContentId = tvShowContentResponse[0].id

    @Test
    fun getAllMovies() {
        val dataSourceFactory =
            Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, ContentEntity>
        Mockito.`when`(local.getAllContentByType("movies")).thenReturn(dataSourceFactory)
        contentRepository.getAllContentByType("movies")
        val contentEntities =
            Resource.success(PagedListUtil.mockPagedList(MockData.generateMockMovies()))
        verify(local).getAllContentByType("movies")
        Assert.assertNotNull(contentEntities.data)
        assertEquals(movieContentResponse.size.toLong(), contentEntities.data?.size?.toLong())
    }

    @Test
    fun getAllTvShow() {
        val dataSourceFactory =
            Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, ContentEntity>
        Mockito.`when`(local.getAllContentByType("tvShow")).thenReturn(dataSourceFactory)
        contentRepository.getAllContentByType("tvShow")
        val contentEntities =
            Resource.success(PagedListUtil.mockPagedList(MockData.generateMockTvShow()))
        verify(local).getAllContentByType("tvShow")
        Assert.assertNotNull(contentEntities.data)
        assertEquals(tvShowContentResponse.size.toLong(), contentEntities.data?.size?.toLong())
    }

    @Test
    fun getMovieContentById() {
        val dummyEntity = MutableLiveData<ContentEntity>()
        dummyEntity.value = MockData.generateMockMovies()[0]
        Mockito.`when`(local.getContentById(movieContentId)).thenReturn(dummyEntity)
        val courseEntities = LiveDataUtils.getValue(
            contentRepository.getContentWithIdAndType(
                movieContentId,
                "movies"
            )
        )
        verify(local).getContentById(movieContentId)
        Assert.assertNotNull(courseEntities.data)
        Assert.assertNotNull(courseEntities.data?.title)
        assertEquals(movieContentResponse[0].title, courseEntities.data?.title)
    }

    @Test
    fun getTvShowContentById() {
        val dummyEntity = MutableLiveData<ContentEntity>()
        dummyEntity.value = MockData.generateMockTvShow()[0]
        Mockito.`when`(local.getContentById(tvShowContentId)).thenReturn(dummyEntity)
        val courseEntities = LiveDataUtils.getValue(
            contentRepository.getContentWithIdAndType(
                tvShowContentId,
                "movies"
            )
        )
        verify(local).getContentById(tvShowContentId)
        Assert.assertNotNull(courseEntities.data)
        Assert.assertNotNull(courseEntities.data?.title)
        assertEquals(tvShowContentResponse[0].title, courseEntities.data?.title)
    }

    @Test
    fun getFavoritedMovies() {
        val dataSourceFactory =
            Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, ContentEntity>
        Mockito.`when`(local.getFavoritedContentsByType("movies")).thenReturn(dataSourceFactory)
        contentRepository.getFavoritedContentByType("movies")
        val contentEntities =
            Resource.success(PagedListUtil.mockPagedList(MockData.generateMockMovies()))
        verify(local).getFavoritedContentsByType("movies")
        Assert.assertNotNull(contentEntities)
        assertEquals(movieContentResponse.size.toLong(), contentEntities.data?.size?.toLong())
    }

    @Test
    fun getFavoritedTvShows() {
        val dataSourceFactory =
            Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, ContentEntity>
        Mockito.`when`(local.getFavoritedContentsByType("tvShow")).thenReturn(dataSourceFactory)
        contentRepository.getFavoritedContentByType("tvShow")
        val contentEntities =
            Resource.success(PagedListUtil.mockPagedList(MockData.generateMockTvShow()))
        verify(local).getFavoritedContentsByType("tvShow")
        Assert.assertNotNull(contentEntities)
        assertEquals(tvShowContentResponse.size.toLong(), contentEntities.data?.size?.toLong())
    }


}