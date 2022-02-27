package com.decagonhq.clads_client.presentation.network

import com.decagonhq.clads_client.presentation.model.GenericResult
import com.decagonhq.clads_client.presentation.model.LoginRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface ClientAPI {

    @POST("/api/v1/customer/login")
    suspend fun loginUser(@Body loginRequest: LoginRequest): GenericResult<String>
}
