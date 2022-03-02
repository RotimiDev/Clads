package com.decagonhq.clads_client.presentation.network

import com.decagonhq.clads_client.data.model.Profile
import com.decagonhq.clads_client.presentation.utils.Constants.Companion.PROFILE_URL
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface ClientAPI{

    @GET(PROFILE_URL)
    suspend fun getProfileDetails(@Header("Authorization") token: String):Response<Profile>
}
