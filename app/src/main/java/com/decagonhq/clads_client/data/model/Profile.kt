package com.decagonhq.clads_client.data.model

data class Profile(
    val message: String,
    val payload: EditProfilePayload,
    val status: Int
)
