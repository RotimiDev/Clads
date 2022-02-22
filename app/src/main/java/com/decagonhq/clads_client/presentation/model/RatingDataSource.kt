package com.decagonhq.clads_client.presentation.model

class RatingDataSource {

    companion object {
        fun createDataSet(): ArrayList<Rating> {
            val list = ArrayList<Rating>()
            list.add(
                Rating(
                    "Amet minim mollit non deserunt ullamco est sit aliqua dolor do amet sint.",
                    "   20.20.2020",

                )
            )

            list.add(
                Rating(
                    "Two Amet minim mollit non deserunt ullamco est sit aliqua dolor do amet sint.",
                    "   17.17.1717",

                )
            )
            return list
        }
    }
}
