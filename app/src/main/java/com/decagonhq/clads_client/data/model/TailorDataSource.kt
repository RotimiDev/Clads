package com.decagonhq.clads_client.data.model

class TailorDataSource {

    companion object {

        fun createDataSet(): ArrayList<Tailor> {
            val list = ArrayList<Tailor>()
            list.add(
                Tailor(
                    "https://renystyles.com/wp-content/uploads/2021/06/Attractive-ankara-styles-2021-for-you-renystyles.com-79.jpg",
                    "JJ Fashionista Limited",
                    "Egbeda, Lagos"
                )
            )

            list.add(
                Tailor(
                    "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home/1.png",
                    "Lola Jegede Threads",
                    "Lekki, Lagos"
                )
            )

            list.add(
                Tailor(
                    "https://www.mynativefashion.com/wp-content/uploads/2020/10/Nigerian-Fashion-Dresses-15-1024x1024.jpg",
                    "Amina Yusuf Fashion",
                    "Zaria, Kaduna"
                )
            )

            list.add(
                Tailor(
                    "https://www.oasdom.com/wp-content/uploads/2018/04/Oasdom.com-fashion-blogs-in-Nigeria-fashion-bloggers-450x579.jpg",
                    "Ehi Ray Clothing",
                    "Benin, Edo"
                )
            )
            return list
        }
    }
}
