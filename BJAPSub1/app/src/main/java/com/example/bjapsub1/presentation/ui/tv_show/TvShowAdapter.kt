package com.example.bjapsub1.presentation.ui.tv_show

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bjapsub1.databinding.ItemMoviesBinding
import com.example.bjapsub1.presentation.Util
import com.example.bjapsub1.presentation.data.MovieEntity
import com.example.bjapsub1.presentation.ui.detail.DetailContentActivity

class TvShowAdapter : RecyclerView.Adapter<TvShowAdapter.ContentViewHolder>() {
    private val listContent = ArrayList<MovieEntity>()

    fun setContent(content: List<MovieEntity>?) {
        if (content == null) return
        this.listContent.clear()
        this.listContent.addAll(content)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentViewHolder {
        val itemMoviesBinding = ItemMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ContentViewHolder(itemMoviesBinding)
    }

    override fun onBindViewHolder(holder: ContentViewHolder, position: Int) {
        val course = listContent[position]
        holder.bind(course)
    }

    override fun getItemCount(): Int = listContent.size

    inner class ContentViewHolder(private val binding: ItemMoviesBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieEntity) {
            with(binding) {
                tvTitle.text = movie.title
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailContentActivity::class.java)
                    intent.putExtra(DetailContentActivity.EXTRA_CONTENT_ID, movie.contentId)
                    intent.putExtra(DetailContentActivity.EXTRA_CONTENT_TYPE,DetailContentActivity.TV_SHOW_KEY)
                    itemView.context.startActivity(intent)
                }
                Util.setPhoto(itemView.context, ivPoster, movie.imagePath, false)
            }
        }
    }
}