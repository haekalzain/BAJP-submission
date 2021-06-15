package com.example.bjapsub2.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.bjapsub2.data.remote.ContentResponse
import com.example.bjapsub2.data.remote.RemoteDataSource

class MoviesRepository private constructor(private val remoteDataSource: RemoteDataSource) : MoviesDataSource {

    companion object {
        @Volatile
        private var instance: MoviesRepository? = null
        fun getInstance(remoteData: RemoteDataSource): MoviesRepository =
                instance ?: synchronized(this) {
                    instance ?: MoviesRepository(remoteData).apply { instance = this }
                }
    }

    override fun getAllMovie(): LiveData<List<ContentEntity>> {
        val moviesResults = MutableLiveData<List<ContentEntity>>()
        remoteDataSource.getAllMovies(object : RemoteDataSource.LoadContentCallback {
            override fun onAllContentReceived(moviesResponses: List<ContentResponse>) {
                val moviesList = ArrayList<ContentEntity>()
                for (response in moviesResponses) {
                    val course = ContentEntity(response.contentId,
                            response.title,
                            response.sinopsis,
                            response.releasedDate,
                            response.genre,
                            response.duration,
                            response.imagePath)
                    moviesList.add(course)
                }
                moviesResults.postValue(moviesList)
            }
        })

        return moviesResults
    }


    override fun getAllTvShow(): LiveData<List<ContentEntity>> {
        val moviesResults = MutableLiveData<List<ContentEntity>>()
        remoteDataSource.getAllTvShow(object : RemoteDataSource.LoadContentCallback {
            override fun onAllContentReceived(moviesResponses: List<ContentResponse>) {
                val moviesList = ArrayList<ContentEntity>()
                for (response in moviesResponses) {
                    val course = ContentEntity(response.contentId,
                            response.title,
                            response.sinopsis,
                            response.releasedDate,
                            response.genre,
                            response.duration,
                            response.imagePath)
                    moviesList.add(course)
                }
                moviesResults.postValue(moviesList)
            }
        })

        return moviesResults
    }

    override fun getMovieWithModules(contentId: String): LiveData<ContentEntity> {
        val courseResult = MutableLiveData<ContentEntity>()
        remoteDataSource.getAllMovies(object : RemoteDataSource.LoadContentCallback {
            override fun onAllContentReceived(moviesResponses: List<ContentResponse>) {
                lateinit var content: ContentEntity
                for (response in moviesResponses) {
                    if (response.contentId == contentId) {
                        content = ContentEntity(response.contentId,
                                response.title,
                                response.sinopsis,
                                response.releasedDate,
                                response.genre,
                                response.duration,
                                response.imagePath)
                    }
                }
                courseResult.postValue(content)
            }
        })
        return courseResult
    }

    override fun getTvShowWithModules(contentId: String): LiveData<ContentEntity> {
        val courseResult = MutableLiveData<ContentEntity>()
        remoteDataSource.getAllTvShow(object : RemoteDataSource.LoadContentCallback {
            override fun onAllContentReceived(tvShowResponses: List<ContentResponse>) {
                lateinit var content: ContentEntity
                for (response in tvShowResponses) {
                    if (response.contentId == contentId) {
                        content = ContentEntity(
                                response.contentId,
                                response.title,
                                response.sinopsis,
                                response.releasedDate,
                                response.genre,
                                response.duration,
                                response.imagePath
                        )
                    }
                }
                courseResult.postValue(content)
            }
        })
        return courseResult
    }
}