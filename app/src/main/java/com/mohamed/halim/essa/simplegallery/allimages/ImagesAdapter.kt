package com.mohamed.halim.essa.simplegallery.allimages

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.mohamed.halim.essa.simplegallery.data.Image
import com.mohamed.halim.essa.simplegallery.databinding.ImagesListItemBinding

import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class ImagesAdapter : ListAdapter<Image, ImageViewHolder>(ImageDiffCallBacks()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}


class ImageViewHolder constructor(val binding: ImagesListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(image: Image) {
        binding.image = image
    }

    companion object {
        fun from(parent: ViewGroup): ImageViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ImagesListItemBinding.inflate(layoutInflater, parent, false)
            return ImageViewHolder(binding)
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
