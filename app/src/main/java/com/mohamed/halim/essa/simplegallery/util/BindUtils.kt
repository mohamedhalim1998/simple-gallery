package com.mohamed.halim.essa.simplegallery.util

import android.content.ContentUris
import android.provider.MediaStore
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide


@BindingAdapter("imageUri")
fun ImageView.imageUri(id: Long) {
    Glide.with(this)
        .load(ContentUris.withAppendedId(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, id))
        .centerCrop()
        .into(this)
}


@BindingAdapter("android:numberText")
fun TextView.numberText(num: Int) {
    text = "$num"
}
