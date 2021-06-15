package com.example.bjapsub3.util

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.bjapsub3.R

class DisplayUtil {
    companion object {


        @JvmStatic
        fun setPhoto(
            context: Context,
            imageView: ImageView,
            moviePath: String,
            isNeedCorners: Boolean
        ) {
            if (isNeedCorners) {
                Glide.with(context)
                    .load(moviePath)
                    .transform(RoundedCorners(20))
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    )
                    .into(imageView)
            } else {
                Glide.with(context)
                    .load(moviePath)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    )
                    .into(imageView)
            }
        }
    }


}