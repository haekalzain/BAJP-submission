package com.example.bjapsub2.presentation.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.GridLayoutManager
import com.example.bjapsub2.data.viewModel.ContentViewModelFactory
import com.example.bjapsub2.databinding.FragmentMoviesBinding
import com.example.bjapsub2.data.viewModel.ContentViewModel

class MoviesFragment : Fragment() {
    private lateinit var fragmentMoviesBinding: FragmentMoviesBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        fragmentMoviesBinding = FragmentMoviesBinding.inflate(layoutInflater, container, false)
        return fragmentMoviesBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {

            val moviesFactory = ContentViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, moviesFactory)[ContentViewModel::class.java]

            val moviesAdapter = MoviesAdapter()

            fragmentMoviesBinding.progressBar.visibility = View.VISIBLE
            viewModel.getMovies().observe(viewLifecycleOwner) { content ->
                fragmentMoviesBinding.progressBar.visibility = View.GONE
                moviesAdapter.setContent(content)
                moviesAdapter.notifyDataSetChanged()
            }
            with(fragmentMoviesBinding.rvMovies) {
                layoutManager = GridLayoutManager(context, 2)
                setHasFixedSize(true)
                adapter = moviesAdapter
            }
        }
    }
}