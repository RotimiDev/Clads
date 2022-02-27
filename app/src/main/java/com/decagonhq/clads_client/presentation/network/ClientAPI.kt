package com.decagonhq.clads_client.presentation.network

import com.decagonhq.clads_client.presentation.model.GenericResult
import com.decagonhq.clads_client.presentation.model.LoginRequest
import com.decagonhq.clads_client.presentation.model.RegistrationPayload
import com.decagonhq.clads_client.presentation.model.RegistrationRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface ClientAPI {

    @POST("/login")
    suspend fun loginUser(@Body loginRequest: LoginRequest): GenericResult<String>

    @POST("/register")
    suspend fun registerUser(@Body registrationRequest: RegistrationRequest): GenericResult<RegistrationPayload>


}
