package com.decagonhq.clads_client.presentation.network

import com.decagonhq.clads_client.data.model.GetImage
import com.decagonhq.clads_client.data.model.UploadImage
import com.decagonhq.clads_client.presentation.utils.Constants.Companion.GET_PROFILE_IMAGE
import com.decagonhq.clads_client.presentation.utils.Constants.Companion.UPLOAD_PROFILE_IMAGE
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ClientAPI {
    @GET(GET_PROFILE_IMAGE)
    suspend fun getProfileImage(@Header("Authorization") token: String): Response<GetImage>

    @POST(UPLOAD_PROFILE_IMAGE)
    suspend fun postProfileImage(@Header("Authorization") token: String, image: MultipartBody.Part) : Response<UploadImage>


}
