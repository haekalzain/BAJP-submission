package com.example.bjapsub3.presentation.ui.tvShow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.GridLayoutManager
import com.example.bjapsub3.data.view_model.ContentViewModel
import com.example.bjapsub3.data.view_model.ContentViewModelFactory
import com.example.bjapsub3.databinding.FragmentTvshowBinding
import com.example.bjapsub3.vo.Status

class TvShowFragment : Fragment() {
    private var fragmentTvshowBinding: FragmentTvshowBinding? = null
    private val binding get() = fragmentTvshowBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentTvshowBinding = FragmentTvshowBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {

            val moviesFactory = ContentViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, moviesFactory)[ContentViewModel::class.java]

            val tvShowAdapter = TvShowAdapter()

            viewModel.getContentByType("tvShow").observe(viewLifecycleOwner) { content ->
                when (content.status) {
                    Status.LOADING -> binding?.progressBar?.visibility = View.VISIBLE
                    Status.SUCCESS -> {
                        binding?.progressBar?.visibility = View.GONE
                        tvShowAdapter.submitList(content.data)
                    }
                    Status.ERROR -> {
                        binding?.progressBar?.visibility = View.GONE
                        Toast.makeText(context, "Terjadi kesalahan saat load data", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            with(binding?.rvShow) {
                this?.layoutManager = GridLayoutManager(context, 2)
                this?.setHasFixedSize(true)
                this?.adapter = tvShowAdapter
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        fragmentTvshowBinding = null
    }
}