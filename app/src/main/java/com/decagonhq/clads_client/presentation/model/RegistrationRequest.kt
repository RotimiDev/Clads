package com.decagonhq.clads_client.presentation.model

data class RegistrationRequest(
    val firstName: String,
    val lastName: String,
    val otherName: String,
    val email: String,
    val password: String,
    val role: String = "CLIENT"
)
