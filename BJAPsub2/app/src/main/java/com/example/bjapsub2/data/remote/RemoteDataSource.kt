package com.example.bjapsub2.data.remote

import android.os.Handler
import android.os.Looper
import com.example.bjapsub2.data.data_utils.EspressoIdlingResources
import com.example.bjapsub2.data.data_utils.JsonHelper

class RemoteDataSource private constructor(private val jsonHelper: JsonHelper){

    private val handler = Handler(Looper.getMainLooper())

    companion object {
        private const val SERVICE_LATENCY_IN_MILLIS: Long = 2000

        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(helper: JsonHelper): RemoteDataSource =
                instance ?: synchronized(this) {
                    instance ?: RemoteDataSource(helper).apply { instance = this }
                }
    }

    fun getAllMovies(callback: LoadContentCallback) {
        EspressoIdlingResources.increment()
        handler.postDelayed({
            callback.onAllContentReceived(jsonHelper.loadMovies())
            EspressoIdlingResources.decrement()
        }, SERVICE_LATENCY_IN_MILLIS)
    }

    fun getAllTvShow(callback: LoadContentCallback) {
        EspressoIdlingResources.increment()
        handler.postDelayed({
            callback.onAllContentReceived(jsonHelper.loadTvShow())
            EspressoIdlingResources.decrement()
        }, SERVICE_LATENCY_IN_MILLIS)
    }

    interface LoadContentCallback {
        fun onAllContentReceived(moviesResponses: List<ContentResponse>)
    }


}