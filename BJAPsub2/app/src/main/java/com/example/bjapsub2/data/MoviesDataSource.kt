package com.example.bjapsub2.data

import androidx.lifecycle.LiveData

interface MoviesDataSource {
    fun getAllMovie(): LiveData<List<ContentEntity>>

    fun getAllTvShow(): LiveData<List<ContentEntity>>

    fun getMovieWithModules(courseId: String): LiveData<ContentEntity>

    fun getTvShowWithModules(courseId: String): LiveData<ContentEntity>
}