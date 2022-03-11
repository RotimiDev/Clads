package com.decagonhq.clads_client.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.decagonhq.clads_client.data.model.Tailor
import com.decagonhq.clads_client.data.repository.ProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArtisanProfileViewModel @Inject constructor(private val repository: ProfileRepository) : ViewModel() {

    fun isFavouriteCall(id:Int):LiveData<List<Tailor>>{
        return repository.isFavourite(id)
    }

    fun insertFavourite(tailor: Tailor) = viewModelScope.launch(Dispatchers.IO){
        repository.insertFavourite(tailor)
    }

    fun deleteFavourite(tailor: Tailor) = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteFavourite(tailor)
    }
}