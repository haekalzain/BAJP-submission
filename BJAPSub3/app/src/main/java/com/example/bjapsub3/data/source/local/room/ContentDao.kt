package com.example.bjapsub3.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.example.bjapsub3.data.source.local.entity.ContentEntity

@Dao
interface ContentDao {
    @Query("SELECT * FROM contentEntity where typeContent=:contentType")
    fun getContent(contentType: String): DataSource.Factory<Int, ContentEntity>

    @Query("SELECT * FROM contentEntity where favorited = 1 AND typeContent=:contentType")
    fun getFavoritedContent(contentType: String): DataSource.Factory<Int, ContentEntity>

    @Transaction
    @Query("SELECT * FROM contentEntity WHERE contentId = :contentId")
    fun getContentById(contentId: String): LiveData<ContentEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertContent(courses: List<ContentEntity>)

    @Update
    fun updateContent(course: ContentEntity)

}