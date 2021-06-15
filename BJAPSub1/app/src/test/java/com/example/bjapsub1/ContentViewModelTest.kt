package com.example.bjapsub1

import com.example.bjapsub1.presentation.ui.ContentViewModel
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class ContentViewModelTest {
    private lateinit var viewModel: ContentViewModel

    @Before
    fun setUp() {
        viewModel = ContentViewModel()
    }

    @Test
    fun getContent() {
        val tvShowEntity = viewModel.getTvShow()
        val movieEntity = viewModel.getMovies()

        Assert.assertNotNull(tvShowEntity)
        Assert.assertEquals(10, tvShowEntity.size)

        Assert.assertNotNull(movieEntity)
        Assert.assertEquals(10, movieEntity.size)

    }
}