package com.decagonhq.clads_client.presentation.network

import com.decagonhq.clads_client.data.model.Profile
import com.decagonhq.clads_client.data.model.Role
import com.decagonhq.clads_client.presentation.model.GenericResult
import com.decagonhq.clads_client.presentation.model.LoginRequest
import com.decagonhq.clads_client.presentation.model.RegistrationPayload
import com.decagonhq.clads_client.presentation.model.RegistrationRequest
import com.decagonhq.clads_client.presentation.network.NetworkConstants.Companion.GOOGLE_LOGIN_URL
import com.decagonhq.clads_client.presentation.network.NetworkConstants.Companion.PROFILE_URL
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ClientAPI {
    @POST("/api/v1/customer/login")
    suspend fun loginUser(@Body loginRequest: LoginRequest): GenericResult<String>

    @POST(GOOGLE_LOGIN_URL)
    suspend fun googleData(
        @Header("Authorization") Bearer: String,
        @Header("Role") role: Role
    ): GenericResult<String>

    @GET(PROFILE_URL)
    suspend fun getProfileDetails(@Header("Authorization") Bearer: String): Response<Profile>

    @POST("/api/v1/customer/register")
    suspend fun registerUser(@Body registrationRequest: RegistrationRequest): GenericResult<RegistrationPayload>
}
