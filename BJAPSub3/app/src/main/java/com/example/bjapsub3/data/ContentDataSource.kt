package com.example.bjapsub3.data

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.example.bjapsub3.data.source.local.entity.ContentEntity
import com.example.bjapsub3.vo.Resource

interface ContentDataSource {

    fun getAllContentByType(contentType: String): LiveData<Resource<PagedList<ContentEntity>>>

    fun getContentWithIdAndType(contentId: String,contentType: String): LiveData<Resource<ContentEntity>>

    fun getFavoritedContentByType(contentType: String): LiveData<PagedList<ContentEntity>>

    fun setContentFavorite(course: ContentEntity, state: Boolean)
}