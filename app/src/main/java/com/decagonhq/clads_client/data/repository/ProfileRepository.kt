package com.decagonhq.clads_client.data.repository

import com.decagonhq.clads_client.presentation.network.ClientAPI
import okhttp3.MultipartBody
import javax.inject.Inject

class ProfileRepository @Inject constructor(private val api: ClientAPI) {
    suspend fun getProfileImage(token: String) = api.getProfileImage(token)

    suspend fun postProfileImage(token: String, image: MultipartBody.Part) = api.postProfileImage(token, image)
}
