package com.example.bjapsub3.data.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.bjapsub3.data.ContentRepository
import com.example.bjapsub3.data.source.local.entity.ContentEntity
import com.example.bjapsub3.vo.Resource

class ContentViewModel(private val contentRepository: ContentRepository) : ViewModel() {

    fun getContentByType(contentType: String): LiveData<Resource<PagedList<ContentEntity>>> =
        contentRepository.getAllContentByType(contentType)
}