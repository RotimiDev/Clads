package com.decagonhq.clads_client.presentation.model

class Rating(
    var rating: String,
    var date: String
) {
    override fun toString(): String {
        return "Rating(rating='$rating', date='$date')"
    }
}
