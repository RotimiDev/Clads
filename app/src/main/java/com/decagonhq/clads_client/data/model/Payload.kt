package com.decagonhq.clads_client.data.model

data class Payload(
    val description: String,
    val downloadUri: String,
    val fileId: String,
    val fileName: String,
    val fileType: String,
    val uploadStatus: Boolean
)
