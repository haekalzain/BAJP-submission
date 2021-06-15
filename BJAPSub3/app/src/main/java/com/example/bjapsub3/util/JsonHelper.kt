package com.example.bjapsub3.util

import android.content.Context
import com.example.bjapsub3.data.source.remote.response.ContentResponse
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

class JsonHelper(private val context: Context) {

    private fun parsingFileToString(fileName: String): String? {
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

    fun loadContent(contentType: String): List<ContentResponse> {
        val list = ArrayList<ContentResponse>()
        try {
            val type = when (contentType) {
                "movies" -> "Movies"
                else -> "TvShow"
            }
            val responseObject = JSONObject(parsingFileToString("${type}Responses.json").toString())
            val listArray = responseObject.getJSONArray(contentType)
            for (i in 0 until listArray.length()) {
                val movie = listArray.getJSONObject(i)

                val id = movie.getString("id")
                val title = movie.getString("title")
                val overview = movie.getString("sinopsis")
                val released = movie.getString("released_date")
                val genre = movie.getString("genre")
                val duration = movie.getString("duration")
                val imagePath = movie.getString("imagePath")

                val courseResponse = ContentResponse(
                    id,
                    title,
                    overview,
                    released,
                    genre,
                    duration,
                    imagePath,
                    contentType
                )
                list.add(courseResponse)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return list
    }
}