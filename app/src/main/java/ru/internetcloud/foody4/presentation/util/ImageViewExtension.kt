package ru.internetcloud.beer.presentation.util

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import ru.internetcloud.foody4.R

fun ImageView.loadImage(imageUri: String) {

    Glide.with(context)
        .load(imageUri)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .placeholder(R.drawable.ic_image)
        .into(this)
}
