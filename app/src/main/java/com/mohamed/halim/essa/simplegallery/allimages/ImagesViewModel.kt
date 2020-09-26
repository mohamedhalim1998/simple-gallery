package com.mohamed.halim.essa.simplegallery.allimages

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.mohamed.halim.essa.simplegallery.data.Image
import com.mohamed.halim.essa.simplegallery.data.Repo
import kotlinx.coroutines.launch
import timber.log.Timber

class ImagesViewModel @ViewModelInject constructor(
    val repo: Repo,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    var images: LiveData<List<Image>> =  repo.getAllImages().asLiveData()

    fun setAlbumId(albumId: Long) {
        images = repo.getImagesFromAlbum(albumId).asLiveData()
    }
}