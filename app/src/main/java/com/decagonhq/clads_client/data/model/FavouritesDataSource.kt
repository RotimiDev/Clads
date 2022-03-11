package com.decagonhq.clads_client.data.model

class FavouritesDataSource {

    companion object {
        fun createDataSet(): ArrayList<FavouritesItem> {
            val list = ArrayList<FavouritesItem>()
            list.add(
                FavouritesItem(
                    "Deca Fashionista",
                    "https://renystyles.com/wp-content/uploads/2021/06/Attractive-ankara-styles-2021-for-you-renystyles.com-25.jpg",
                )
            )
            list.add(
                FavouritesItem(
                    "Browndo Gowns",
                    "https://renystyles.com/wp-content/uploads/2021/06/Attractive-ankara-styles-2021-for-you-renystyles.com-50.jpg",
                )
            )
            list.add(
                FavouritesItem(
                    "Ade and Johnson Suites",
                    "https://renystyles.com/wp-content/uploads/2021/06/Attractive-ankara-styles-2021-for-you-renystyles.com-1.jpg",
                )
            )
            list.add(
                FavouritesItem(
                    "Simpson's place",
                    "https://www.mynativefashion.com/wp-content/uploads/2020/10/Nigerian-Fashion-Dresses-16-1024x1024.jpg",
                )
            )
            return list
        }
    }
}
