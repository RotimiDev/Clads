package com.decagonhq.clads_client.data.repsitory

import com.decagonhq.clads_client.data.model.Profile
import com.decagonhq.clads_client.presentation.network.ClientAPI
import com.decagonhq.clads_client.presentation.utils.ApiCallHandler
import com.decagonhq.clads_client.presentation.utils.Resource
import retrofit2.Response
import javax.inject.Inject

class ProfileRepository @Inject constructor(private  val api: ClientAPI){

    suspend fun getProfile(token: String): Resource<Response<Profile>> {
      return ApiCallHandler.safeApiCall {
          api.getProfileDetails(token)
      }
    }
}