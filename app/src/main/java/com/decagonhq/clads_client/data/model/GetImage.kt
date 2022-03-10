package com.decagonhq.clads_client.data.model

data class GetImage(
    val message: String,
    val payload: List<Payload>,
    val status: Int
)
