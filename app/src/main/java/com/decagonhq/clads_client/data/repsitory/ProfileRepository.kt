package com.decagonhq.clads_client.data.repsitory

import com.decagonhq.clads_client.presentation.network.ClientAPI
import com.decagonhq.clads_client.presentation.utils.ApiCallHandler
import javax.inject.Inject

class ProfileRepository @Inject constructor(private val api: ClientAPI){

    suspend fun getProfile(token: String) = api.getProfileDetails(token)

    }
