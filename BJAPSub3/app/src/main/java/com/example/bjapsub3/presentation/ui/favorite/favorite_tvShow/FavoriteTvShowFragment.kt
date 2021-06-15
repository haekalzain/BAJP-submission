package com.example.bjapsub3.presentation.ui.favorite.favorite_tvShow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.bjapsub3.R
import com.example.bjapsub3.data.view_model.ContentViewModelFactory
import com.example.bjapsub3.data.view_model.FavoriteViewModel
import com.example.bjapsub3.databinding.FragmentFavoriteMoviesBinding
import com.example.bjapsub3.databinding.FragmentFavoriteTvShowBinding
import com.example.bjapsub3.presentation.ui.favorite.favorite_movie.FavoriteMovieAdapter
import com.google.android.material.snackbar.Snackbar

class FavoriteTvShowFragment : Fragment() {
    lateinit var favTvShowBinding: FragmentFavoriteTvShowBinding

    private val binding get() = favTvShowBinding

    private lateinit var viewModel: FavoriteViewModel
    private lateinit var moviesAdapter: FavoriteMovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        favTvShowBinding =
            FragmentFavoriteTvShowBinding.inflate(layoutInflater, container, false)
        return favTvShowBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        itemTouchHelper.attachToRecyclerView(favTvShowBinding.rvTvshowFav)

        if (activity != null) {
            val factory = ContentViewModelFactory.getInstance(requireActivity())

            viewModel = ViewModelProvider(this, factory)[FavoriteViewModel::class.java]
            binding.rvTvshowFav.visibility = View.VISIBLE

            moviesAdapter = FavoriteMovieAdapter(false)
            binding.rvTvshowFav.layoutManager = GridLayoutManager(context, 2)
            binding.rvTvshowFav.setHasFixedSize(true)
            binding.rvTvshowFav.adapter = moviesAdapter

            binding.progressBar.visibility = View.VISIBLE
            viewModel.getFavoriteContentByType("tvShow").observe(viewLifecycleOwner) { content ->
                binding.progressBar.visibility = View.GONE
                if (content.size < 1) {
                    binding.notFoundAnim.visibility = View.VISIBLE
                    binding.rvTvshowFav.visibility = View.GONE
                } else {
                    binding.notFoundAnim.visibility = View.GONE
                    binding.rvTvshowFav.visibility = View.VISIBLE
                    moviesAdapter.submitList(content)
                }
            }
        }
    }

    private val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.Callback() {
        override fun getMovementFlags(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder
        ): Int =
            makeMovementFlags(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)

        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean = true

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            if (view != null) {
                val swipedPosition = viewHolder.adapterPosition
                val contentEntity = moviesAdapter.getSwipedData(swipedPosition)
                contentEntity?.let { viewModel.setFavorite(it) }

                val snackbar =
                    Snackbar.make(view as View, R.string.message_undo, Snackbar.LENGTH_LONG)
                snackbar.setAction(R.string.message_ok) { v ->
                    contentEntity?.let { viewModel.setFavorite(it) }
                }
                snackbar.show()
            }
        }
    })
}