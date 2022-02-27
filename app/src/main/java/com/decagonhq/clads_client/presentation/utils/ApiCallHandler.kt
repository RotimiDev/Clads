package com.decagonhq.clads_client.presentation.utils

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

object ApiCallHandler {
    suspend fun <T> safeApiCall(apiCall: suspend () -> T): Resource<T> {
        return withContext(Dispatchers.IO) {
            try {
                Resource.Success(apiCall.invoke())
            } catch (t: Throwable) {
                when (t) {
                    is HttpException -> {
                        val code = t.code()
                        Resource.Error(null, "$code")
                    }
                    is IOException -> {
                        Resource.Error(null, "IO Error! Could not connect to the internet")
                    }
                    else -> {
                        Resource.Error(null, "An error occurred")
                    }
                }
            }
        }
    }
}
