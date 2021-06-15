package com.example.bjapsub3.data.view_model

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.bjapsub3.data.ContentRepository
import com.example.bjapsub3.di.Injection

class ContentViewModelFactory private constructor(private val mMoviesRepository: ContentRepository) : ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ContentViewModelFactory? = null

        fun getInstance(context: Context): ContentViewModelFactory =
            instance
                ?: synchronized(this) {
                    instance
                        ?: ContentViewModelFactory(Injection.provideMoviesRepository(context)).apply {
                            instance = this
                        }
                }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(ContentViewModel::class.java) -> {
                ContentViewModel(mMoviesRepository) as T
            }
            modelClass.isAssignableFrom(DetailContentViewModel::class.java) -> {
                DetailContentViewModel(mMoviesRepository) as T
            }

            modelClass.isAssignableFrom(FavoriteViewModel::class.java) -> {
                FavoriteViewModel(mMoviesRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
    }
}