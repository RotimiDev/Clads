package com.decagonhq.clads_client.presentation.model

data class UpdateProfileRequest (
    val firstName: String,
    val lastName: String,
    val otherName: String,
    val state: String,
    val gender: String)

