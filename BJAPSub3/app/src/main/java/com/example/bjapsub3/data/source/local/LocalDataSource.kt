package com.example.bjapsub3.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.example.bjapsub3.data.source.local.entity.ContentEntity
import com.example.bjapsub3.data.source.local.room.ContentDao

class LocalDataSource private constructor(
    private val mMoviesDao: ContentDao
) {

    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(moviesDao: ContentDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(moviesDao)
    }

    fun getAllContentByType(contentType: String): DataSource.Factory<Int, ContentEntity> =
        mMoviesDao.getContent(contentType)

    fun getFavoritedContentsByType(contentType: String): DataSource.Factory<Int, ContentEntity> =
        mMoviesDao.getFavoritedContent(contentType)

    fun getContentById(courseId: String): LiveData<ContentEntity> =
        mMoviesDao.getContentById(courseId)

    fun insertContent(courses: List<ContentEntity>) = mMoviesDao.insertContent(courses)

    fun setContentFavorite(course: ContentEntity, newState: Boolean) {
        course.favorited = newState
        mMoviesDao.updateContent(course)
    }


}