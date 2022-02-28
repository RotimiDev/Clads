package com.decagonhq.clads_client.presentation.ui.utils

import com.decagonhq.clads_client.data.model.FavouritesItem

class FavouritesDataSource {

    companion object {
        fun createDataSet(): ArrayList<FavouritesItem> {
            val list = ArrayList<FavouritesItem>()
            list.add(
                FavouritesItem(
                    "JJ Fashionista Limited",
                    "https://picsum.photos/id/1/200/300",
                )
            )
            list.add(
                FavouritesItem(
                    "Browndo Gowns",
                    "https://picsum.photos/id/1/200/300",
                )
            )
            list.add(
                FavouritesItem(
                    "Ade and Johnson Suites",
                    "https://picsum.photos/id/1/200/300",
                )
            )
            list.add(
                FavouritesItem(
                    "Simpson's place",
                    "https://picsum.photos/id/1/200/300",
                )
            )
            return list
        }
    }
}
