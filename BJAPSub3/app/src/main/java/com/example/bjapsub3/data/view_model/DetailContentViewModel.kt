package com.example.bjapsub3.data.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.bjapsub3.data.ContentRepository
import com.example.bjapsub3.data.source.local.entity.ContentEntity
import com.example.bjapsub3.presentation.ui.detail.DetailContentActivity.Companion.MOVIE_KEY
import com.example.bjapsub3.vo.Resource

class DetailContentViewModel(private val moviesRepository: ContentRepository) : ViewModel() {

    val movieContentId = MutableLiveData<String>()
    val tvShowContentId = MutableLiveData<String>()

    fun setSelectedMovieContent(contentId: String) {
        this.movieContentId.value = contentId
    }

    fun setSelectedTvShowContent(contentId: String) {
        this.tvShowContentId.value = contentId
    }

    fun setFavorite(contentType: String) {
        val moduleResource: Resource<ContentEntity>?
        when (contentType) {
            MOVIE_KEY ->
                moduleResource = getMovie.value
            else ->
                moduleResource = getTvShow.value
        }
        if (moduleResource != null) {
            val contentWithModule = moduleResource.data
            if (contentWithModule != null) {
                val newState = !contentWithModule.favorited
                moviesRepository.setContentFavorite(contentWithModule, newState)
            }
        }
    }


    var getMovie: LiveData<Resource<ContentEntity>> =
        Transformations.switchMap(movieContentId) { movieContentId ->
            moviesRepository.getContentWithIdAndType(movieContentId, "movies")
        }

    var getTvShow: LiveData<Resource<ContentEntity>> =
        Transformations.switchMap(tvShowContentId) { tvShowContentId ->
            moviesRepository.getContentWithIdAndType(tvShowContentId, "tvShow")
        }
}