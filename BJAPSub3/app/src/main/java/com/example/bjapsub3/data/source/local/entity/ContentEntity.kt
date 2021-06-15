package com.example.bjapsub3.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contentEntity")
data class ContentEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "contentId")
    var contentId: String,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "sinopsis")
    var sinopsis: String,

    @ColumnInfo(name = "releasedDate")
    var releasedDate: String,

    @ColumnInfo(name = "genre")
    var genre: String,

    @ColumnInfo(name = "duration")
    var duration: String,

    @ColumnInfo(name = "favorited")
    var favorited: Boolean = false,

    @ColumnInfo(name = "imagePath")
    var imagePath: String,

    @ColumnInfo(name = "typeContent")
    var typeContent: String
)