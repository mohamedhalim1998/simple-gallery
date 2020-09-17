package com.mohamed.halim.essa.simplegallery.singleimage

import android.content.ContentUris
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.mohamed.halim.essa.simplegallery.data.Image

import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.davemorrissey.labs.subscaleview.ImageSource
import com.mohamed.halim.essa.simplegallery.databinding.ImagePagerItemBinding
import timber.log.Timber

class ImagePagerAdapter(val fullScreenControl: FullScreenControl) :
    ListAdapter<Image, ImagePagerAdapter.ImageViewHolder>(ImageDiffCallBacks()) {
    private var fullScreen = true
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ImagePagerItemBinding.inflate(layoutInflater, parent, false)
        return ImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(getItem(position), fullScreenControl)
    }

    inner class ImageViewHolder constructor(val binding: ImagePagerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(image: Image, fullScreenControl: FullScreenControl) {
            val uri =
                ContentUris.withAppendedId(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, image.id)
            binding.loading.visibility = View.VISIBLE
            binding.imageFull.setImage(ImageSource.uri(uri))
            binding.imageFull.setMinimumDpi(100)
            binding.imageFull.setDoubleTapZoomDpi(100)
            binding.loading.visibility = View.GONE
            binding.imageFull.setOnClickListener {
                if (fullScreen) {
                    fullScreenControl.exitFullScreen()
                } else {
                    fullScreenControl.enterFullScreen()
                }
                fullScreen = fullScreen.not()
            }
        }


    }

}


class ImageDiffCallBacks : DiffUtil.ItemCallback<Image>() {
    override fun areItemsTheSame(oldItem: Image, newItem: Image): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Image, newItem: Image): Boolean {
        return oldItem == newItem
    }
}

interface FullScreenControl {
    fun enterFullScreen()
    fun exitFullScreen()
}