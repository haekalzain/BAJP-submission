package com.example.bjapsub3.data.source.remote.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ContentResponse(
    var id: String,
    var title: String,
    var overview: String,
    var released: String,
    var genre: String,
    var duration: String,
    var imagePath: String,
    var typeContent:String
) : Parcelable