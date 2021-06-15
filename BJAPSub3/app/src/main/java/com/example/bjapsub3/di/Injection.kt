package com.example.bjapsub3.di

import android.content.Context
import com.example.bjapsub3.data.ContentRepository
import com.example.bjapsub3.data.source.local.LocalDataSource
import com.example.bjapsub3.data.source.local.room.ContentDatabase
import com.example.bjapsub3.data.source.remote.RemoteDataSource
import com.example.bjapsub3.util.AppExecutors
import com.example.bjapsub3.util.JsonHelper

object Injection {
    fun provideMoviesRepository(context: Context): ContentRepository {

        val database = ContentDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance(JsonHelper(context))

        val localDataSource = LocalDataSource.getInstance(database.moviesDao())

        val appExecutors = AppExecutors()

        return ContentRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
}