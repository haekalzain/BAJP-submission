package com.example.bjapsub1.presentation.ui.tv_show

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.bjapsub1.databinding.FragmentTvshowBinding
import com.example.bjapsub1.presentation.ui.ContentViewModel

class TvShowFragments : Fragment() {
    lateinit var fragmentTvShowBinding: FragmentTvshowBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        fragmentTvShowBinding = FragmentTvshowBinding.inflate(inflater, container, false)
        return fragmentTvShowBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[ContentViewModel::class.java]
            val tvShow = viewModel.getTvShow()

            val adapter = TvShowAdapter()
            adapter.setContent(tvShow)
            with(fragmentTvShowBinding.rvShow) {
                layoutManager = GridLayoutManager(context, 2)
                setHasFixedSize(true)
                this.adapter = adapter
            }
        }
    }
}