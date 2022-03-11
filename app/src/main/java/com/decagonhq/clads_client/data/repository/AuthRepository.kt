package com.decagonhq.clads_client.data.repository

import com.decagonhq.clads_client.data.model.GenericResult
import com.decagonhq.clads_client.data.model.LoginRequest
import com.decagonhq.clads_client.data.model.RegistrationPayload
import com.decagonhq.clads_client.data.model.RegistrationRequest
import com.decagonhq.clads_client.data.model.Role
import com.decagonhq.clads_client.network.ClientAPI
import com.decagonhq.clads_client.utils.ApiCallHandler
import com.decagonhq.clads_client.utils.Resource
import retrofit2.Response
import javax.inject.Inject

class AuthRepository @Inject constructor(private val api: ClientAPI) {

    suspend fun loginUser(loginRequest: LoginRequest): Resource<GenericResult<String>> {
        return ApiCallHandler.safeApiCall {
            api.loginUser(loginRequest)
        }
    }

    suspend fun googleUser(bearer: String, role: Role): Resource<GenericResult<String>> {
        return ApiCallHandler.safeApiCall {
            api.googleData(bearer, role)
        }
    }
    suspend fun registerUser(registrationRequest: RegistrationRequest): Resource<GenericResult<RegistrationPayload>> {
        return ApiCallHandler.safeApiCall {
            api.registerUser(registrationRequest)
        }
    }

    suspend fun verifyUserMail(token: String): Response<GenericResult<String>> {
        return api.verifyAuthToken(token)
    }
}
