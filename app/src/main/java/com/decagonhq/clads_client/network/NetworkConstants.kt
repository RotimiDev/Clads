package com.decagonhq.clads_client.network

class NetworkConstants {

    companion object {
        const val BASE_URL = "https://clads-service.herokuapp.com/"
        const val GET_PROFILE_IMAGE = "api/v1/download/image"
        const val UPLOAD_PROFILE_IMAGE = "api/v1/upload"
        const val GOOGLE_LOGIN_URL = "api/v1/login/google"
        const val PROFILE_URL = "api/v1/customer/me/profile"
        const val VERIFY_USER_URL = "api/v1/confirm"
    }
}
