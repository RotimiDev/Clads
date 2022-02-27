package com.decagonhq.clads_client.data.repository

import com.decagonhq.clads_client.presentation.model.GenericResult
import com.decagonhq.clads_client.presentation.model.LoginRequest
import com.decagonhq.clads_client.presentation.network.ClientAPI
import com.decagonhq.clads_client.presentation.utils.ApiCallHandler
import com.decagonhq.clads_client.presentation.utils.Resource
import javax.inject.Inject


class AuthRepository @Inject constructor(private  val api: ClientAPI) {

    suspend fun loginUser( loginRequest: LoginRequest): Resource<GenericResult<String>> {
        return ApiCallHandler.safeApiCall {
            api.loginUser(loginRequest)
        }
    }
}