package com.mohamed.halim.essa.simplegallery.albums

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.mohamed.halim.essa.simplegallery.data.Album
import com.mohamed.halim.essa.simplegallery.data.Repo
import kotlinx.coroutines.launch

class AlbumsViewModel @ViewModelInject constructor(
    val repo: Repo,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    val albums: LiveData<List<Album>> = repo.getAllAlbums().asLiveData()
}