package com.decagonhq.clads_client.data.repository

import com.decagonhq.clads_client.presentation.network.ClientAPI
import javax.inject.Inject

class ProfileRepository @Inject constructor(private val api: ClientAPI) {

    suspend fun getProfile(token: String) = api.getProfileDetails(token)
}
