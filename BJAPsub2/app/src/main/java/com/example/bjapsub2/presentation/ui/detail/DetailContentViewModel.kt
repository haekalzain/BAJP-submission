package com.example.bjapsub2.presentation.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.bjapsub2.data.data_utils.MockData
import com.example.bjapsub2.data.ContentEntity
import com.example.bjapsub2.data.MoviesRepository

class DetailContentViewModel(private val moviesRepository: MoviesRepository) : ViewModel() {
    private lateinit var movieContentId: String
    private lateinit var tvShowContentId: String

    fun setSelectedMovieContent(contentId: String) {
        this.movieContentId = contentId
    }

    fun setSelectedTvShowContent(contentId: String) {
        this.tvShowContentId = contentId
    }


    fun getMovie(): LiveData<ContentEntity> = moviesRepository.getMovieWithModules(movieContentId)

    fun getTvShow(): LiveData<ContentEntity> = moviesRepository.getTvShowWithModules(tvShowContentId)
}