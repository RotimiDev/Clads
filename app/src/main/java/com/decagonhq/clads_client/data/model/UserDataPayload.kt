package com.decagonhq.clads_client.data.model

data class UserDataPayload(
    val artisanId: List<Any>,
    val authprovider: String,
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
