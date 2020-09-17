package com.mohamed.halim.essa.simplegallery.singleimage

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.mohamed.halim.essa.simplegallery.data.Image
import com.mohamed.halim.essa.simplegallery.data.Repo
import kotlinx.coroutines.launch

class SingleImageViewModel @ViewModelInject constructor(
    val repo: Repo,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    val images: LiveData<List<Image>> = repo.getAllImages().asLiveData()
}