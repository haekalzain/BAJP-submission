package com.example.bjapsub2.presentation.ui.tv_show

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.GridLayoutManager
import com.example.bjapsub2.data.viewModel.ContentViewModelFactory
import com.example.bjapsub2.databinding.FragmentTvshowBinding
import com.example.bjapsub2.data.viewModel.ContentViewModel

class TvShowFragments : Fragment() {
    lateinit var fragmentTvShowBinding: FragmentTvshowBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        fragmentTvShowBinding = FragmentTvshowBinding.inflate(inflater, container, false)
        return fragmentTvShowBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val moviesFactory = ContentViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, moviesFactory)[ContentViewModel::class.java]
            val adapter = TvShowAdapter()
            fragmentTvShowBinding.progressBar.visibility = View.VISIBLE
            viewModel.getTvShow().observe(viewLifecycleOwner) { content ->
                fragmentTvShowBinding.progressBar.visibility = View.GONE
                adapter.setContent(content)
                adapter.notifyDataSetChanged()
            }
            with(fragmentTvShowBinding.rvShow) {
                layoutManager = GridLayoutManager(context, 2)
                setHasFixedSize(true)
                this.adapter = adapter
            }
        }
    }
}