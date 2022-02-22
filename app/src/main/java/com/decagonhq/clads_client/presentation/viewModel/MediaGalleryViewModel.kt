package com.decagonhq.clads_client.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.decagonhq.clads_client.data.model.MediaFragmentModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MediaGalleryViewModel @Inject constructor() : ViewModel() {
    private var _mediaGallery = MutableLiveData<List<MediaFragmentModel>>()
    val mediaGallery: LiveData<List<MediaFragmentModel>> get() = _mediaGallery

    fun addToGallery(newPicture: MediaFragmentModel) {
        val tempGallery: ArrayList<MediaFragmentModel> = arrayListOf()
        _mediaGallery.value?.let { tempGallery.addAll(it) }
        tempGallery.add(newPicture)
        _mediaGallery.value = tempGallery
    }
}
