package com.example.bjapsub3.presentation.ui.movie

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.bjapsub3.data.source.local.entity.ContentEntity
import com.example.bjapsub3.databinding.ItemMoviesBinding
import com.example.bjapsub3.presentation.ui.detail.DetailContentActivity
import com.example.bjapsub3.util.DisplayUtil

class MoviesAdapter: PagedListAdapter<ContentEntity, MoviesAdapter.ContentViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ContentEntity>() {
            override fun areItemsTheSame(oldItem: ContentEntity, newItem: ContentEntity): Boolean {
                return oldItem.contentId == newItem.contentId
            }
            override fun areContentsTheSame(oldItem: ContentEntity, newItem: ContentEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentViewHolder {
        val itemsAcademyBinding = ItemMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ContentViewHolder(itemsAcademyBinding)
    }

    override fun onBindViewHolder(holder: ContentViewHolder, position: Int) {
        val content = getItem(position)
        if (content != null) {
            holder.bind(content)
        }
    }

    class ContentViewHolder(private val binding: ItemMoviesBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(content: ContentEntity) {
            with(binding) {
                tvTitle.text = content.title
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailContentActivity::class.java)
                    intent.putExtra(DetailContentActivity.EXTRA_CONTENT_ID, content.contentId)
                    intent.putExtra(DetailContentActivity.EXTRA_CONTENT_TYPE, DetailContentActivity.MOVIE_KEY)
                    itemView.context.startActivity(intent)
                }
                DisplayUtil.setPhoto(itemView.context, ivPoster, content.imagePath, false)
            }
        }
    }
}