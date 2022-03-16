package com.decagonhq.clads_client.network

import com.decagonhq.clads_client.data.model.GenericResult
import com.decagonhq.clads_client.data.model.GetImage
import com.decagonhq.clads_client.data.model.LoginRequest
import com.decagonhq.clads_client.data.model.Profile
import com.decagonhq.clads_client.data.model.RegistrationPayload
import com.decagonhq.clads_client.data.model.RegistrationRequest
import com.decagonhq.clads_client.data.model.Role
import com.decagonhq.clads_client.data.model.UploadImage
import com.decagonhq.clads_client.network.NetworkConstants.Companion.GET_PROFILE_IMAGE
import com.decagonhq.clads_client.network.NetworkConstants.Companion.GOOGLE_LOGIN_URL
import com.decagonhq.clads_client.network.NetworkConstants.Companion.PROFILE_URL
import com.decagonhq.clads_client.network.NetworkConstants.Companion.UPLOAD_PROFILE_IMAGE
import com.decagonhq.clads_client.network.NetworkConstants.Companion.VERIFY_USER_URL
import com.decagonhq.clads_client.presentation.model.UpdateProfilePayload
import com.decagonhq.clads_client.presentation.model.UpdateProfileRequest
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

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

    @GET(VERIFY_USER_URL)
    suspend fun verifyAuthToken(
        @Query("token") token: String
    ): Response<GenericResult<String>>

    @GET(GET_PROFILE_IMAGE)
    suspend fun getProfileImage(@Header("Authorization") token: String): Response<GetImage>

    @POST(UPLOAD_PROFILE_IMAGE)
    suspend fun postProfileImage(@Header("Authorization") token: String, image: MultipartBody.Part): Response<UploadImage>

    @PUT("/api/v1/customer/me/profile")
    suspend fun updateUserProfile(
        @Header("Authorization") Bearer: String,
        @Body updateProfileRequest: UpdateProfileRequest
    ): GenericResult<UpdateProfilePayload>
}
