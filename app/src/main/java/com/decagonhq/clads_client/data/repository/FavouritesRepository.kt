package com.decagonhq.clads_client.data.repository

import androidx.lifecycle.LiveData
import com.decagonhq.clads_client.data.local.FavouritesDatabase
import com.decagonhq.clads_client.data.model.Tailor
import javax.inject.Inject

class FavouritesRepository @Inject constructor(private val favouritesDatabase: FavouritesDatabase) {

    suspend fun insertFavourite (tailor: Tailor) = favouritesDatabase.getTailorDao().upsert(tailor)
    suspend fun deleteFavourite(tailor: Tailor) = favouritesDatabase.getTailorDao().deleteArtisan(tailor)
    fun getFavourites():LiveData<List<Tailor>> = favouritesDatabase.getTailorDao().getAllFavourites()
}