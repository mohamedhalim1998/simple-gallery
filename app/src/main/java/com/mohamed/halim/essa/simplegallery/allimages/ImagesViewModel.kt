package com.mohamed.halim.essa.simplegallery.allimages

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.mohamed.halim.essa.simplegallery.data.Image
import com.mohamed.halim.essa.simplegallery.data.Repo


class ImagesViewModel @ViewModelInject constructor(
    val repo: Repo,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    var images: LiveData<List<Image>> = repo.getAllImages().asLiveData()

    fun setAlbumId(albumId: Long) {
        if (albumId != -1L)
            images = repo.getImagesFromAlbum(albumId).asLiveData()
        else
            images = repo.getAllImages().asLiveData()

    }
}