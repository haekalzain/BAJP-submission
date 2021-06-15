package com.example.bjapsub2.presentation.ui.movie

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bjapsub2.data.ContentEntity
import com.example.bjapsub2.databinding.ItemMoviesBinding
import com.example.bjapsub2.utils.DisplayUtil
import com.example.bjapsub2.presentation.ui.detail.DetailContentActivity

class MoviesAdapter : RecyclerView.Adapter<MoviesAdapter.ContentViewHolder>() {
    private var listContent = ArrayList<ContentEntity>()

    fun setContent(content: List<ContentEntity>?) {
        if (content == null) return
        this.listContent.clear()
        this.listContent.addAll(content)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentViewHolder {
        val itemsAcademyBinding = ItemMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ContentViewHolder(itemsAcademyBinding)
    }

    override fun onBindViewHolder(holder: ContentViewHolder, position: Int) {
        val course = listContent[position]
        holder.bind(course)
    }

    override fun getItemCount(): Int = listContent.size


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