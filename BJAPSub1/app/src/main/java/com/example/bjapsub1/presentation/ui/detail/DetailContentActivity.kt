package com.example.bjapsub1.presentation.ui.detail

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.bjapsub1.R
import com.example.bjapsub1.databinding.ActivityDetailContentBinding
import com.example.bjapsub1.databinding.ContentDetailBinding
import com.example.bjapsub1.presentation.Util
import com.example.bjapsub1.presentation.data.MovieEntity
import jp.wasabeef.glide.transformations.BlurTransformation

class DetailContentActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_CONTENT_ID = "EXTRA_CONTENT_ID"
        const val EXTRA_CONTENT_TYPE = "EXTRA_CONTENT_TYPE"
        const val MOVIE_KEY = "MOVIE_KEY"
        const val TV_SHOW_KEY = "TV_SHOW_KEY"
    }

    private lateinit var detailContentBinding: ContentDetailBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activityDetailContentBinding = ActivityDetailContentBinding.inflate(layoutInflater)
        detailContentBinding = activityDetailContentBinding.detailContent

        setContentView(activityDetailContentBinding.root)

        setSupportActionBar(activityDetailContentBinding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val movieModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[DetailContentViewModel::class.java]

        val extras = intent.extras
        if (extras != null) {
            val contentId = extras.getString(EXTRA_CONTENT_ID)
            val contentType = extras.getString(EXTRA_CONTENT_TYPE)
            if (contentId != null) {
                if (contentType.equals(MOVIE_KEY)) {
                    movieModel.setSelectedMovieContent(contentId)
                    populateContent(movieModel.getMovies())
                } else {
                    movieModel.setSelectedTvShowContent(contentId)
                    populateContent(movieModel.getTvShow())
                }
            }
        }
    }

    private fun populateContent(movieEntity: MovieEntity) {
        detailContentBinding.tvTitle.text = movieEntity.title
        detailContentBinding.tvOverview.text = movieEntity.sinopsis
        detailContentBinding.tvGenre.text = movieEntity.genre
        detailContentBinding.tvDuration.text =
            resources.getString(R.string.duration, movieEntity.duration)
        detailContentBinding.tvReleaseDate.text =
            resources.getString(R.string.released_date, movieEntity.releasedDate)

        Util.setPhoto(this, detailContentBinding.imagePoster, movieEntity.imagePath, true)

        Glide.with(this)
            .load(movieEntity.imagePath)
            .apply(RequestOptions.bitmapTransform(BlurTransformation(6)))
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_loading)
                    .error(R.drawable.ic_error)
            )
            .into(detailContentBinding.imagePoster2)

        detailContentBinding.ibShare.setOnClickListener {
            val intent = Intent()
            intent.type = "text/plain"
            intent.action = Intent.ACTION_SEND
            intent.putExtra(
                Intent.EXTRA_TEXT,
                "Don't forget to Watch ${movieEntity.title} at MovieQ app."
            )
            startActivity(intent)
        }
    }
}