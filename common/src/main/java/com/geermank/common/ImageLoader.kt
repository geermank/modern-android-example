package com.geermank.common

import android.widget.ImageView
import com.squareup.picasso.Picasso

object ImageLoader {

    fun loadImage(from: String, target: ImageView) {
        Picasso.get().load(from).into(target)
    }
}
