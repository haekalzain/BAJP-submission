package com.example.bjapsub2.data.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.bjapsub2.data.MoviesRepository
import com.example.bjapsub2.di.Injection
import com.example.bjapsub2.presentation.ui.detail.DetailContentViewModel

class ContentViewModelFactory private constructor(private val mMoviesRepository: MoviesRepository) : ViewModelProvider.NewInstanceFactory() {

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
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
    }
}