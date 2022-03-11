package com.decagonhq.clads_client.data.model

data class GenericResult<T>(
    val message: String,
    val payload: T,
    val status: Int
)
