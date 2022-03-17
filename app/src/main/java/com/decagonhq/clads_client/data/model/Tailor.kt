package com.decagonhq.clads_client.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "favourite artisans")
@Parcelize
data class Tailor(
    @PrimaryKey
    val id: Int,
    var image: String,
    var name: String,
    var location: String,
    var style: String,
    var phone: String = "08146397088",

) : Parcelable
