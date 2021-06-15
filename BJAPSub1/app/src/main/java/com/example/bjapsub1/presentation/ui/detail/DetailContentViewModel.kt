package com.example.bjapsub1.presentation.ui.detail

import androidx.lifecycle.ViewModel
import com.example.bjapsub1.presentation.data.MockData
import com.example.bjapsub1.presentation.data.MovieEntity

class DetailContentViewModel : ViewModel() {
    private lateinit var movieContentId: String
    private lateinit var tvShowContentId: String

    fun setSelectedMovieContent(contentId: String) {
        this.movieContentId = contentId
    }

    fun setSelectedTvShowContent(contentId: String) {
        this.tvShowContentId = contentId
    }

    fun getMovies(): MovieEntity {
        lateinit var movie: MovieEntity
        val movieEntities = MockData.generateMockMovies()
        for (movieEntity in movieEntities) {
            if (movieEntity.contentId == movieContentId) {
                movie = movieEntity
            }
        }
        return movie
    }

    fun getTvShow(): MovieEntity {
        lateinit var tvShow: MovieEntity
        val tvShowList = MockData.generateMockTvShow()
        for (tvShowEntity in tvShowList) {
            if (tvShowEntity.contentId == tvShowContentId) {
                tvShow = tvShowEntity
            }
        }
        return tvShow
    }
}