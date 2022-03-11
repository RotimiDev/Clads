package com.decagonhq.clads_client.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Tailor(
    var image: String,
    var name: String,
    var location: String,
    var phone:String = "08146397088"
):Parcelable
