package com.example.bjapsub1.presentation.ui

import androidx.lifecycle.ViewModel
import com.example.bjapsub1.presentation.data.MockData
import com.example.bjapsub1.presentation.data.MovieEntity

class ContentViewModel : ViewModel() {

    fun getTvShow(): List<MovieEntity> = MockData.generateMockTvShow()

    fun getMovies(): List<MovieEntity> = MockData.generateMockMovies()
}