package com.decagonhq.clads_client.presentation.model

data class UpdateProfileRequest(
    val deliveryAddresses: List<DeliveryAddresse>,
    val email: String,
    val firstName: String,
    val gender: String,
    val lastName: String,
    val measurements: List<Any>? = null,
    val phoneNumber: String? = null
)
