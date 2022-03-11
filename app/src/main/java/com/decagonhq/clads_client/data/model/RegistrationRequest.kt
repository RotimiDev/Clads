package com.decagonhq.clads_client.data.model

data class RegistrationRequest(
    val firstName: String,
    val lastName: String,
    val otherName: String,
    val email: String,
    val password: String,
    val role: String = "CLIENT"
)
