package com.example.bjapsub3.view_model

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.paging.PositionalDataSource
import com.example.bjapsub3.data.ContentRepository
import com.example.bjapsub3.data.source.local.entity.ContentEntity
import com.example.bjapsub3.data.view_model.FavoriteViewModel
import com.example.bjapsub3.util.MockData
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
class FavoriteViewModelTest {
    private lateinit var viewModel: FavoriteViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var moviesRepository: ContentRepository

    @Mock
    private lateinit var observer: Observer<PagedList<ContentEntity>>

    @Before
    fun setUp() {
        viewModel = FavoriteViewModel(moviesRepository)
    }

    @Test
    fun `getFavoritedMovie should be success`() {
        val expected = MutableLiveData<PagedList<ContentEntity>>()
        expected.value = PagedTestDataSources.snapshot(MockData.generateMockMovies())

        Mockito.`when`(moviesRepository.getFavoritedContentByType("movies")).thenReturn(expected)

        viewModel.getFavoriteContentByType("movies").observeForever(observer)
        Mockito.verify(observer).onChanged(expected.value)

        val expectedValue = expected.value
        val actualValue = viewModel.getFavoriteContentByType("movies").value
        Assert.assertEquals(expectedValue, actualValue)
        Assert.assertEquals(expectedValue?.snapshot(), actualValue?.snapshot())
        Assert.assertEquals(expectedValue?.size, actualValue?.size)
    }

    @Test
    fun `getFavoritedMovie should be success but data is empty`() {
        val expected = MutableLiveData<PagedList<ContentEntity>>()
        expected.value = PagedTestDataSources.snapshot()

        Mockito.`when`(moviesRepository.getFavoritedContentByType("movies")).thenReturn(expected)

        viewModel.getFavoriteContentByType("movies").observeForever(observer)
        Mockito.verify(observer).onChanged(expected.value)

        val actualValueDataSize = viewModel.getFavoriteContentByType("movies").value?.size
        Assert.assertTrue("size of data should be 0, actual is $actualValueDataSize", actualValueDataSize == 0)
    }


    @Test
    fun `getFavoritedTvShow should be success`() {
        val expected = MutableLiveData<PagedList<ContentEntity>>()
        expected.value = PagedTestDataSources.snapshot(MockData.generateMockMovies())

        Mockito.`when`(moviesRepository.getFavoritedContentByType("tvShow")).thenReturn(expected)

        viewModel.getFavoriteContentByType("tvShow").observeForever(observer)
        Mockito.verify(observer).onChanged(expected.value)

        val expectedValue = expected.value
        val actualValue = viewModel.getFavoriteContentByType("tvShow").value
        Assert.assertEquals(expectedValue, actualValue)
        Assert.assertEquals(expectedValue?.snapshot(), actualValue?.snapshot())
        Assert.assertEquals(expectedValue?.size, actualValue?.size)
    }

    @Test
    fun `getFavoritedTvShow should be success but data is empty`() {
        val expected = MutableLiveData<PagedList<ContentEntity>>()
        expected.value = PagedTestDataSources.snapshot()

        Mockito.`when`(moviesRepository.getFavoritedContentByType("tvShow")).thenReturn(expected)

        viewModel.getFavoriteContentByType("tvShow").observeForever(observer)
        Mockito.verify(observer).onChanged(expected.value)

        val actualValueDataSize = viewModel.getFavoriteContentByType("tvShow").value?.size
        Assert.assertTrue("size of data should be 0, actual is $actualValueDataSize", actualValueDataSize == 0)
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

        override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<ContentEntity>) {
            callback.onResult(items, 0, items.size)
        }

        override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<ContentEntity>) {
            val start = params.startPosition
            val end = params.startPosition + params.loadSize
            callback.onResult(items.subList(start, end))
        }
    }
}