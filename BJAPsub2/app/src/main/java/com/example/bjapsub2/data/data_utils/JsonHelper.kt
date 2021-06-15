package com.example.bjapsub2.data.data_utils

import android.content.Context
import com.example.bjapsub2.data.remote.ContentResponse
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

class JsonHelper(private val context: Context) {
    private fun convertFileToString(fileName: String): String? {
        return try {
            val `is` = context.assets.open(fileName)
            val buffer = ByteArray(`is`.available())
            `is`.read(buffer)
            `is`.close()
            String(buffer)

        } catch (ex: IOException) {
            ex.printStackTrace()
            null
        }
    }

    fun loadMovies(): List<ContentResponse> {
        val list = ArrayList<ContentResponse>()
        try {
            val responseObject = JSONObject(convertFileToString("MoviesResponses.json").toString())
            val listArray = responseObject.getJSONArray("movies")
            for (i in 0 until listArray.length()) {
                val movie = listArray.getJSONObject(i)

                val id = movie.getString("id")
                val title = movie.getString("title")
                val sinopsis = movie.getString("sinopsis")
                val releasedDate = movie.getString("released_date")
                val genre = movie.getString("genre")
                val duration = movie.getString("duration")
                val imagePath = movie.getString("imagePath")

                val contentResponse = ContentResponse(id, title, sinopsis, releasedDate, genre, duration, imagePath)
                list.add(contentResponse)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return list
    }

    fun loadTvShow(): List<ContentResponse> {
        val list = ArrayList<ContentResponse>()
        try {
            val responseObject = JSONObject(convertFileToString("TvShowResponses.json").toString())
            val listArray = responseObject.getJSONArray("tvShow")
            for (i in 0 until listArray.length()) {
                val tvShow = listArray.getJSONObject(i)

                val id = tvShow.getString("id")
                val title = tvShow.getString("title")
                val sinopsis = tvShow.getString("sinopsis")
                val releasedDate = tvShow.getString("released_date")
                val genre = tvShow.getString("genre")
                val duration = tvShow.getString("duration")
                val imagePath = tvShow.getString("imagePath")

                val contentResponse = ContentResponse(id, title, sinopsis, releasedDate, genre, duration, imagePath)
                list.add(contentResponse)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return list
    }
}