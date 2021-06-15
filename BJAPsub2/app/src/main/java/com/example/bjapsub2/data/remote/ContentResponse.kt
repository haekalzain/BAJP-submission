package com.example.bjapsub2.data.remote

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ContentResponse(
        var contentId: String,
        var title: String,
        var sinopsis: String,
        var releasedDate: String,
        var genre: String,
        var duration: String,
        var imagePath: String
):Parcelable