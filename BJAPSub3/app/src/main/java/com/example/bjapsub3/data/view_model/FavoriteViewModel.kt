package com.example.bjapsub3.data.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.bjapsub3.data.ContentRepository
import com.example.bjapsub3.data.source.local.entity.ContentEntity

class FavoriteViewModel(private val contentRepository: ContentRepository) : ViewModel() {

    fun getFavoriteContentByType(contentType:String): LiveData<PagedList<ContentEntity>> = contentRepository.getFavoritedContentByType(contentType)

    fun setFavorite(contentEntity: ContentEntity) {
        val newState = !contentEntity.favorited
        contentRepository.setContentFavorite(contentEntity, newState)
    }
}