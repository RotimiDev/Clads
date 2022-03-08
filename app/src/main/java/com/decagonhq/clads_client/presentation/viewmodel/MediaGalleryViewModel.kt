package com.decagonhq.clads_client.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.decagonhq.clads_client.data.model.PhotoGalleryModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MediaGalleryViewModel @Inject constructor() : ViewModel() {
    private var _mediaGallery = MutableLiveData<List<PhotoGalleryModel>>()
    val photoGallery: LiveData<List<PhotoGalleryModel>> get() = _mediaGallery

    fun addToGallery(newPicture: PhotoGalleryModel) {
        val tempGallery: ArrayList<PhotoGalleryModel> = arrayListOf()
        _mediaGallery.value?.let { tempGallery.addAll(it) }
        tempGallery.add(newPicture)
        _mediaGallery.value = tempGallery
    }
}
