package com.decagonhq.clads_client.presentation.model

import com.google.gson.annotations.SerializedName

data class RegistrationPayload(
    val artisanId: List<Any>,
    @SerializedName("authProvider")
    val authProvider: String,
    val createdAt: String,
    val deliveryAddresses: List<Any>,
    val email: String,
    val firstName: String,
    val gender: String,
    val lastName: String,
    val measurements: List<Any>,
    val phoneNumber: String,
    val updateAt: Any
)