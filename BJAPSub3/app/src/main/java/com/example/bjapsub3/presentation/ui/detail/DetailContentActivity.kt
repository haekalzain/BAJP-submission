package com.example.bjapsub3.presentation.ui.detail

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.bjapsub3.R
import com.example.bjapsub3.data.source.local.entity.ContentEntity
import com.example.bjapsub3.data.view_model.ContentViewModelFactory
import com.example.bjapsub3.data.view_model.DetailContentViewModel
import com.example.bjapsub3.databinding.ActivityContentDetailBinding
import com.example.bjapsub3.databinding.ContentDetailBinding
import com.example.bjapsub3.util.DisplayUtil
import com.example.bjapsub3.vo.Status
import jp.wasabeef.glide.transformations.BlurTransformation

class DetailContentActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_CONTENT_ID = "EXTRA_CONTENT_ID"
        const val EXTRA_CONTENT_TYPE = "EXTRA_CONTENT_TYPE"
        const val MOVIE_KEY = "MOVIE_KEY"
        const val TV_SHOW_KEY = "TV_SHOW_KEY"
    }

    private lateinit var detailContentBinding: ContentDetailBinding

    private var menu: Menu? = null

    private lateinit var movieModel: DetailContentViewModel
    private lateinit var activityDetailContentBinding: ActivityContentDetailBinding
    private lateinit var contentType: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityDetailContentBinding = ActivityContentDetailBinding.inflate(layoutInflater)
        detailContentBinding = activityDetailContentBinding.detailContent

        setContentView(activityDetailContentBinding.root)

        setSupportActionBar(activityDetailContentBinding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val contentRepository = ContentViewModelFactory.getInstance(this)

        movieModel = ViewModelProvider(
            this,
            contentRepository
        )[DetailContentViewModel::class.java]

        val extras = intent.extras
        if (extras != null) {
            val contentId = extras.getString(EXTRA_CONTENT_ID)
            contentType = extras.getString(EXTRA_CONTENT_TYPE).toString()
            if (contentId != null) {
                activityDetailContentBinding.loadingLottie.visibility = View.VISIBLE
                activityDetailContentBinding.content.visibility = View.INVISIBLE

                if (contentType == MOVIE_KEY) {
                    movieModel.setSelectedMovieContent(contentId)
                    movieModel.getMovie.observe(this) { courseWithModuleResource ->
                        when (courseWithModuleResource.status) {
                            Status.LOADING -> activityDetailContentBinding.loadingLottie.visibility =
                                View.VISIBLE
                            Status.SUCCESS -> if (courseWithModuleResource.data != null) {
                                activityDetailContentBinding.loadingLottie.visibility = View.GONE
                                activityDetailContentBinding.content.visibility = View.VISIBLE
                                populateContent(courseWithModuleResource.data)
                            }
                            Status.ERROR -> {
                                activityDetailContentBinding.loadingLottie.visibility = View.GONE
                                Toast.makeText(
                                    applicationContext,
                                    "Terjadi kesalahan saat load data",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }

                } else {
                    movieModel.setSelectedTvShowContent(contentId)
                    movieModel.getTvShow.observe(this) { courseWithModuleResource ->
                        when (courseWithModuleResource.status) {
                            Status.LOADING -> activityDetailContentBinding.loadingLottie.visibility =
                                View.VISIBLE
                            Status.SUCCESS -> if (courseWithModuleResource.data != null) {
                                activityDetailContentBinding.loadingLottie.visibility = View.GONE
                                activityDetailContentBinding.content.visibility = View.VISIBLE
                                populateContent(courseWithModuleResource.data)
                            }
                            Status.ERROR -> {
                                activityDetailContentBinding.loadingLottie.visibility = View.GONE
                                Toast.makeText(
                                    applicationContext,
                                    "Terjadi kesalahan saat load data",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_home, menu)
        this.menu = menu
        if (contentType == MOVIE_KEY) {
            movieModel.getMovie.observe(this) { courseWithModule ->
                when (courseWithModule.status) {
                    Status.LOADING -> activityDetailContentBinding.loadingLottie.visibility =
                        View.VISIBLE
                    Status.SUCCESS -> if (courseWithModule.data != null) {
                        activityDetailContentBinding.loadingLottie.visibility = View.GONE
                        val state = courseWithModule.data.favorited
                        setFavoriteState(state)
                    }
                    Status.ERROR -> {
                        activityDetailContentBinding.loadingLottie.visibility = View.GONE
                        Toast.makeText(applicationContext, "Terjadi kesalahan saat load data", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        } else {
            movieModel.getTvShow.observe(this) { courseWithModule ->
                when (courseWithModule.status) {
                    Status.LOADING -> activityDetailContentBinding.loadingLottie.visibility =
                        View.VISIBLE
                    Status.SUCCESS -> if (courseWithModule.data != null) {
                        activityDetailContentBinding.loadingLottie.visibility = View.GONE
                        val state = courseWithModule.data.favorited
                        setFavoriteState(state)
                    }
                    Status.ERROR -> {
                        activityDetailContentBinding.loadingLottie.visibility = View.GONE
                        Toast.makeText(applicationContext, "Terjadi kesalahan saat load data", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_favorite) {
            movieModel.setFavorite(contentType)
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setFavoriteState(state: Boolean) {
        if (menu == null) return
        val menuItem = menu?.findItem(R.id.action_favorite)
        if (state) {
            menuItem?.icon = ContextCompat.getDrawable(this, R.drawable.ic_favorite_red)
        } else {
            menuItem?.icon = ContextCompat.getDrawable(this, R.drawable.ic_favorite_border)
        }
    }
}
