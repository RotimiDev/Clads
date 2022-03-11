package com.decagonhq.clads_client.data.repository

import androidx.lifecycle.LiveData
import com.decagonhq.clads_client.data.local.FavouritesDatabase
import com.decagonhq.clads_client.data.model.Tailor
import com.decagonhq.clads_client.network.ClientAPI
import okhttp3.MultipartBody
import javax.inject.Inject

class ProfileRepository @Inject constructor(
    private val api: ClientAPI,
    private val favouritesDatabase: FavouritesDatabase
    ) {

    fun isFavourite(id:Int): LiveData<List<Tailor>> = favouritesDatabase.getTailorDao().isFavourite(id)

    suspend fun insertFavourite (tailor: Tailor) = favouritesDatabase.getTailorDao().upsert(tailor)

    suspend fun deleteFavourite(tailor: Tailor) = favouritesDatabase.getTailorDao().deleteArtisan(tailor)

    suspend fun getProfileImage(token: String) = api.getProfileImage(token)

    suspend fun postProfileImage(token: String, image: MultipartBody.Part) = api.postProfileImage(token, image)

    suspend fun getProfile(token: String) = api.getProfileDetails(token)
}
