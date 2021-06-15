package com.example.bjapsub2.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.bjapsub2.data.remote.ContentResponse
import com.example.bjapsub2.data.remote.RemoteDataSource
import java.util.ArrayList

class FakeContentRepository(private val remoteDataSource: RemoteDataSource) : MoviesDataSource {

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
        val tvResults = MutableLiveData<List<ContentEntity>>()
        remoteDataSource.getAllTvShow(object : RemoteDataSource.LoadContentCallback {
            override fun onAllContentReceived(tvResponse: List<ContentResponse>) {
                val moviesList = ArrayList<ContentEntity>()
                for (tvShow in tvResponse) {
                    val course = ContentEntity(tvShow.contentId,
                        tvShow.title,
                        tvShow.sinopsis,
                        tvShow.releasedDate,
                        tvShow.genre,
                        tvShow.duration,
                        tvShow.imagePath)
                    moviesList.add(course)
                }
                tvResults.postValue(moviesList)
            }
        })

        return tvResults
    }


    override fun getMovieWithModules(contentId: String): LiveData<ContentEntity> {
        val courseResult = MutableLiveData<ContentEntity>()
        remoteDataSource.getAllMovies(object : RemoteDataSource.LoadContentCallback {
            override fun onAllContentReceived(moviesResponses: List<ContentResponse>) {
                lateinit var course: ContentEntity
                for (response in moviesResponses) {
                    if (response.contentId == contentId) {
                        course = ContentEntity(response.contentId,
                            response.title,
                            response.sinopsis,
                            response.releasedDate,
                            response.genre,
                            response.duration,
                            response.imagePath)
                    }
                }
                courseResult.postValue(course)
            }
        })
        return courseResult
    }

    override fun getTvShowWithModules(contentId: String): LiveData<ContentEntity> {
        val courseResult = MutableLiveData<ContentEntity>()
        remoteDataSource.getAllTvShow(object : RemoteDataSource.LoadContentCallback {
            override fun onAllContentReceived(moviesResponses: List<ContentResponse>) {
                lateinit var course: ContentEntity
                for (response in moviesResponses) {
                    if (response.contentId == contentId) {
                        course = ContentEntity(response.contentId,
                            response.title,
                            response.sinopsis,
                            response.releasedDate,
                            response.genre,
                            response.duration,
                            response.imagePath)
                    }
                }
                courseResult.postValue(course)
            }
        })
        return courseResult
    }


}