package com.example.bjapsub3.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.bjapsub3.data.source.local.entity.ContentEntity

@Database(entities = [ContentEntity::class],
    version = 1,
    exportSchema = false)
abstract class ContentDatabase : RoomDatabase() {
    abstract fun moviesDao(): ContentDao

    companion object {

        @Volatile
        private var INSTANCE: ContentDatabase? = null

        fun getInstance(context: Context): ContentDatabase =
            INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    ContentDatabase::class.java,
                    "Content.db"
                ).build().apply {
                    INSTANCE = this
                }
            }
    }
}