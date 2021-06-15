package com.example.bjapsub2.data.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.bjapsub2.data.data_utils.MockData
import com.example.bjapsub2.data.ContentEntity
import com.example.bjapsub2.data.MoviesRepository

class ContentViewModel(private val moviesRepository: MoviesRepository) : ViewModel() {

    fun getMovies(): LiveData<List<ContentEntity>> = moviesRepository.getAllMovie()
    fun getTvShow(): LiveData<List<ContentEntity>> = moviesRepository.getAllTvShow()
}