package com.mohamed.halim.essa.simplegallery.allimages

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.mohamed.halim.essa.simplegallery.data.Image
import com.mohamed.halim.essa.simplegallery.data.Repo
import kotlinx.coroutines.launch

class ImagesViewModel @ViewModelInject constructor(
    val repo: Repo,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    val images: LiveData<List<Image>> = repo.getAllImages().asLiveData()

    init {
        updateImagesCache()
    }

    fun updateImagesCache() {
        viewModelScope.launch {
            repo.updateImages()
        }
    }
}