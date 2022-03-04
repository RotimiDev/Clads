package com.decagonhq.clads_client.data.model

data class UserBody(
    val message: String,
    val payload: UserDataPayload,
    val status: Int
)