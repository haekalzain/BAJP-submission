package com.example.bjapsub2.presentation.ui.detail

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.bjapsub2.R
import com.example.bjapsub2.data.ContentEntity
import com.example.bjapsub2.data.viewModel.ContentViewModelFactory
import com.example.bjapsub2.databinding.ActivityDetailContentBinding
import com.example.bjapsub2.databinding.ContentDetailBinding
import com.example.bjapsub2.utils.DisplayUtil
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

        val contentRepository = ContentViewModelFactory.getInstance(this)

        val movieModel = ViewModelProvider(
                this,
                contentRepository
        )[DetailContentViewModel::class.java]

        val extras = intent.extras
        if (extras != null) {
            val contentId = extras.getString(EXTRA_CONTENT_ID)
            val contentType = extras.getString(EXTRA_CONTENT_TYPE)
            if (contentId != null) {
                activityDetailContentBinding.loadingLottie.visibility = View.VISIBLE
                activityDetailContentBinding.content.visibility = View.INVISIBLE

                if (contentType.equals(MOVIE_KEY)) {
                    movieModel.setSelectedMovieContent(contentId)
                    movieModel.getMovie().observe(this) {
                        activityDetailContentBinding.loadingLottie.visibility = View.GONE
                        activityDetailContentBinding.content.visibility = View.VISIBLE
                        populateContent(it)
                    }
                } else {
                    movieModel.setSelectedTvShowContent(contentId)
                    movieModel.getTvShow().observe(this) {
                        activityDetailContentBinding.loadingLottie.visibility = View.GONE
                        activityDetailContentBinding.content.visibility = View.VISIBLE
                        populateContent(it)
                    }
                }
            }
        }
    }

    private fun populateContent(contentEntity: ContentEntity) {
        detailContentBinding.tvTitle.text = contentEntity.title
        detailContentBinding.tvOverview.text = contentEntity.sinopsis
        detailContentBinding.tvGenre.text = contentEntity.genre
        detailContentBinding.tvDuration.text =
                resources.getString(R.string.duration, contentEntity.duration)
        detailContentBinding.tvReleaseDate.text =
                resources.getString(R.string.released_date, contentEntity.releasedDate)

        DisplayUtil.setPhoto(this, detailContentBinding.imagePoster, contentEntity.imagePath, true)

        Glide.with(this)
                .load(contentEntity.imagePath)
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
                    "Don't forget to Watch ${contentEntity.title} at MovieQ app."
            )
            startActivity(intent)
        }
    }
}