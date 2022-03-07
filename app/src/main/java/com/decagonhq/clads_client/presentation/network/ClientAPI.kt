package com.decagonhq.clads_client.presentation.network

import com.decagonhq.clads_client.data.model.Role
import com.decagonhq.clads_client.presentation.model.GenericResult
import com.decagonhq.clads_client.presentation.model.LoginRequest
import com.decagonhq.clads_client.presentation.network.NetworkConstants.Companion.GOOGLE_LOGIN_URL
import com.google.android.gms.common.api.Api
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface ClientAPI {
    @POST("/api/v1/customer/login")
    suspend fun loginUser(@Body loginRequest: LoginRequest): GenericResult<String>

    @POST(GOOGLE_LOGIN_URL)
    suspend fun googleData(
        @Header("Authorization") Bearer: String,
        @Header("Role") role : Role
    ): GenericResult<String>
}
