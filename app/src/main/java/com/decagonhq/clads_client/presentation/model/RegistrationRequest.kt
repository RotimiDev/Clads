package com.decagonhq.clads_client.presentation.model

data class RegistrationRequest(
    val country: String,
    val email: String,
    val firstName: String,
    val gender: String,
    val lastName: String,
    val password: String,
    val phoneNumber: String,
    val role: String,
    val thumbnail: String
)
