package com.example.bjapsub3.view_model

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.paging.PositionalDataSource
import com.example.bjapsub3.data.ContentRepository
import com.example.bjapsub3.data.source.local.entity.ContentEntity
import com.example.bjapsub3.data.view_model.ContentViewModel
import com.example.bjapsub3.util.MockData
import com.example.bjapsub3.vo.Resource
import junit.framework.Assert.assertEquals
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import java.util.concurrent.Executors

@RunWith(MockitoJUnitRunner::class)
class ContentViewModelTest {
    private lateinit var viewModel: ContentViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var moviesRepository: ContentRepository

    @Mock
    private lateinit var observer: Observer<Resource<PagedList<ContentEntity>>>

    @Mock
    private lateinit var pagedList: PagedList<ContentEntity>

    @Before
    fun setUp() {
        viewModel = ContentViewModel(moviesRepository)
    }


    @Test
    fun `getMovies should be error`() {
        val contentMessage = "Something happen!"
        val movies = MutableLiveData<Resource<PagedList<ContentEntity>>>()
        movies.value = Resource.error(contentMessage, null)

        Mockito.`when`(moviesRepository.getAllContentByType("movies")).thenReturn(movies)

        viewModel.getContentByType("movies").observeForever(observer)
        Mockito.verify(observer).onChanged(movies.value)

        val actualMessage = viewModel.getContentByType("movies").value?.message
        Assert.assertEquals(contentMessage, actualMessage)
    }

    @Test
    fun `getMovies should be success`() {
        val movieContent = PagedTestDataSources.snapshot(MockData.generateMockMovies())
        val movies = MutableLiveData<Resource<PagedList<ContentEntity>>>()
        movies.value = Resource.success(movieContent)

        Mockito.`when`(moviesRepository.getAllContentByType("movies")).thenReturn(movies)

        viewModel.getContentByType("movies").observeForever(observer)
        Mockito.verify(observer).onChanged(movies.value)

        val moviesValue = movies.value
        val actualValue = viewModel.getContentByType("movies").value
        assertEquals(moviesValue, actualValue)
        assertEquals(moviesValue?.data, actualValue?.data)
        assertEquals(moviesValue?.data?.size, actualValue?.data?.size)
    }

    @Test
    fun `getMovies should be success but data is empty`() {
        val moviesContent = PagedTestDataSources.snapshot()
        val movies = MutableLiveData<Resource<PagedList<ContentEntity>>>()
        movies.value = Resource.success(moviesContent)

        Mockito.`when`(moviesRepository.getAllContentByType("movies")).thenReturn(movies)

        viewModel.getContentByType("movies").observeForever(observer)
        Mockito.verify(observer).onChanged(movies.value)

        val actualValueDataSize = viewModel.getContentByType("movies").value?.data?.size
        Assert.assertTrue(
            "size of data should be 0, actual is $actualValueDataSize",
            actualValueDataSize == 0
        )
    }

    @Test
    fun `getTvShows should be error`() {
        val contentMessage = "Something happen!"
        val movies = MutableLiveData<Resource<PagedList<ContentEntity>>>()
        movies.value = Resource.error(contentMessage, null)

        Mockito.`when`(moviesRepository.getAllContentByType("tvShow")).thenReturn(movies)

        viewModel.getContentByType("tvShow").observeForever(observer)
        Mockito.verify(observer).onChanged(movies.value)

        val actualMessage = viewModel.getContentByType("tvShow").value?.message
        Assert.assertEquals(contentMessage, actualMessage)
    }


    @Test
    fun `getTvShows should be success`() {
        val tvShowsContent = PagedTestDataSources.snapshot(MockData.generateMockTvShow())
        val tvShows = MutableLiveData<Resource<PagedList<ContentEntity>>>()
        tvShows.value = Resource.success(tvShowsContent)

        Mockito.`when`(moviesRepository.getAllContentByType("tvShow")).thenReturn(tvShows)

        viewModel.getContentByType("tvShow").observeForever(observer)
        Mockito.verify(observer).onChanged(tvShows.value)

        val tvShowsValue = tvShows.value
        val actualValue = viewModel.getContentByType("tvShow").value
        assertEquals(tvShowsValue, actualValue)
        assertEquals(tvShowsValue?.data, actualValue?.data)
        assertEquals(tvShowsValue?.data?.size, actualValue?.data?.size)
    }

    @Test
    fun `getTvShows should be success but data is empty`() {
        val tvShowContent = PagedTestDataSources.snapshot()
        val tvShows = MutableLiveData<Resource<PagedList<ContentEntity>>>()
        tvShows.value = Resource.success(tvShowContent)

        Mockito.`when`(moviesRepository.getAllContentByType("tvShow")).thenReturn(tvShows)

        viewModel.getContentByType("tvShow").observeForever(observer)
        Mockito.verify(observer).onChanged(tvShows.value)

        val actualValueDataSize = viewModel.getContentByType("tvShow").value?.data?.size
        Assert.assertTrue(
            "size of data should be 0, actual is $actualValueDataSize",
            actualValueDataSize == 0
        )
    }

    class PagedTestDataSources private constructor(private val items: List<ContentEntity>) : PositionalDataSource<ContentEntity>() {
        companion object {
            fun snapshot(items: List<ContentEntity> = listOf()): PagedList<ContentEntity> {
                return PagedList.Builder(PagedTestDataSources(items), 10)
                    .setNotifyExecutor(Executors.newSingleThreadExecutor())
                    .setFetchExecutor(Executors.newSingleThreadExecutor())
                    .build()
            }
        }

        override fun loadInitial(
            params: LoadInitialParams,
            callback: LoadInitialCallback<ContentEntity>
        ) {
            callback.onResult(items, 0, items.size)
        }

        override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<ContentEntity>) {
            val start = params.startPosition
            val end = params.startPosition + params.loadSize
            callback.onResult(items.subList(start, end))
        }
    }
}