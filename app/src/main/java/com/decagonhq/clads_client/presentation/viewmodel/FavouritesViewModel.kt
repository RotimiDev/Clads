package com.decagonhq.clads_client.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.decagonhq.clads_client.data.model.Tailor
import com.decagonhq.clads_client.data.repository.FavouritesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavouritesViewModel @Inject constructor(private val repository: FavouritesRepository):ViewModel() {

    fun getAllFavourites(): LiveData<List<Tailor>> {
        return repository.getFavourites()
    }
}