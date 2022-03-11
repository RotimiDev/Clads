package com.decagonhq.clads_client.utils

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.HttpException
import java.io.IOException

object ApiCallHandler {

    private const val MESSAGE_KEY = "message"
    suspend fun <T> safeApiCall(apiCall: suspend () -> T): Resource<T> {
        return withContext(Dispatchers.IO) {
            var apiResponse: T? = null
            try {
                apiResponse = apiCall.invoke()
                Resource.Success(apiResponse)
            } catch (t: Throwable) {
                when (t) {
                    is HttpException -> {
                        val errorResponse = t.response()?.errorBody().let {
                            if (it != null) {
                                getErrorMessage(it)
                            }
                        }
                        Resource.Error(apiResponse, "$errorResponse")
                    }
                    is IOException -> {
                        Resource.Error(apiResponse, "IO Error! Could not connect to the internet")
                    }
                    else -> {
                        Resource.Error(apiResponse, "An error occurred")
                    }
                }
            }
        }
    }

    private fun getErrorMessage(responseBody: ResponseBody): String {
        return try {
            val jsonObject = JSONObject(responseBody.string())
            when {
                jsonObject.has(MESSAGE_KEY) -> jsonObject.getString(MESSAGE_KEY)
                else -> "Something wrong happened"
            }
        } catch (e: Exception) {
            "Something wrong happened"
        }
    }
}
