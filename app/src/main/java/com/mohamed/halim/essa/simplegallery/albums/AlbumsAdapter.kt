package com.mohamed.halim.essa.simplegallery.albums

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil

import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mohamed.halim.essa.simplegallery.data.Album
import com.mohamed.halim.essa.simplegallery.databinding.AlbumListItemBinding

class AlbumsAdapter(val albumClickListener: AlbumClickListener) :
    ListAdapter<Album, AlbumViewHolder>(AlbumDiffCallBacks()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        return AlbumViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        holder.bind(getItem(position), albumClickListener)
    }

}


class AlbumViewHolder constructor(val binding: AlbumListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(album: Album, albumClickListener: AlbumClickListener) {
        binding.album = album
        binding.albumClickListener = albumClickListener
    }

    companion object {
        fun from(parent: ViewGroup): AlbumViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = AlbumListItemBinding.inflate(layoutInflater, parent, false)
            return AlbumViewHolder(binding)
        }
    }

}

class AlbumDiffCallBacks : DiffUtil.ItemCallback<Album>() {
    override fun areItemsTheSame(oldItem: Album, newItem: Album): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Album, newItem: Album): Boolean {
        return oldItem == newItem
    }
}

class AlbumClickListener(val clickListener: (albumId: Long) -> Unit) {
    fun onCLick(albumId: Long) {
        clickListener(albumId)
    }
}