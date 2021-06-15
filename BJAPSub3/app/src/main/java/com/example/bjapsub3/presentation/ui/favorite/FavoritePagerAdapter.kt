package com.example.bjapsub3.presentation.ui.favorite

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.bjapsub3.R
import com.example.bjapsub3.presentation.ui.favorite.favorite_movie.FavoriteMoviesFragment
import com.example.bjapsub3.presentation.ui.favorite.favorite_tvShow.FavoriteTvShowFragment

class FavoritePagerAdapter(private val mContext: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(R.string.fav_movies, R.string.fav_tv_show)
    }

    override fun getItem(position: Int): Fragment =
        when (position) {
            0 -> FavoriteMoviesFragment()
            1 -> FavoriteTvShowFragment()
            else -> Fragment()
        }

    override fun getPageTitle(position: Int): CharSequence? = mContext.resources.getString(
        TAB_TITLES[position]
    )

    override fun getCount(): Int = TAB_TITLES.size

}