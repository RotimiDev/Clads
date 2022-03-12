package com.decagonhq.clads_client.presentation.model

data class UpdateProfilePayload(
    val id: Long,
    val firstName: String,
    val lastName: String,
    val role: String,
    val thumbnail: String,
    val email: String,
    val phoneNumber: String,
    val gender: String,
    val country: String
)
