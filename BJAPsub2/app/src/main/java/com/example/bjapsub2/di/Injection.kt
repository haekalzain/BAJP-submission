package com.example.bjapsub2.di

import android.content.Context
import com.example.bjapsub2.data.MoviesRepository
import com.example.bjapsub2.data.data_utils.JsonHelper
import com.example.bjapsub2.data.remote.RemoteDataSource

object Injection {

    fun provideMoviesRepository(context: Context): MoviesRepository {

        val remoteDataSource = RemoteDataSource.getInstance(JsonHelper(context))

        return MoviesRepository.getInstance(remoteDataSource)
    }
}