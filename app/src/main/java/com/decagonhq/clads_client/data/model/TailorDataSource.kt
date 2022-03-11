package com.decagonhq.clads_client.data.model

class TailorDataSource {

    companion object {

        fun createDataSet(): ArrayList<Tailor> {
            val list = ArrayList<Tailor>()
            list.add(
                Tailor(
                    1,
                    "https://renystyles.com/wp-content/uploads/2021/06/Attractive-ankara-styles-2021-for-you-renystyles.com-79.jpg",
                    "JJ Fashionista Limited",
                    "Egbeda, Lagos"
                )
            )

            list.add(
                Tailor(
                    2,
                    "https://renystyles.com/wp-content/uploads/2021/06/Attractive-ankara-styles-2021-for-you-renystyles.com-11.jpg",
                    "Lola Jegede Threads",
                    "Lekki, Lagos"
                )
            )

            list.add(
                Tailor(
                    3,
                    "https://www.mynativefashion.com/wp-content/uploads/2020/10/Nigerian-Fashion-Dresses-15-1024x1024.jpg",
                    "Amina Yusuf Fashion",
                    "Zaria, Kaduna"
                )
            )

            list.add(
                Tailor(
                    4,
                    "https://www.oasdom.com/wp-content/uploads/2018/04/Oasdom.com-fashion-blogs-in-Nigeria-fashion-bloggers-450x579.jpg",
                    "Ehi Ray Clothing",
                    "Benin, Edo"
                )
            )

            list.add(
                Tailor(
                    5,
                    "https://renystyles.com/wp-content/uploads/2021/06/Attractive-ankara-styles-2021-for-you-renystyles.com-25.jpg",
                    "Deca Fashionista",
                    "Jos, Plateau"
                )
            )
            list.add(
                Tailor(
                    6,
                    "https://renystyles.com/wp-content/uploads/2021/06/Attractive-ankara-styles-2021-for-you-renystyles.com-50.jpg",
                    "Browndo Gowns",
                    "Umahia, Abia"
                )
            )
            list.add(
                Tailor(
                    7,
                    "https://renystyles.com/wp-content/uploads/2021/06/Attractive-ankara-styles-2021-for-you-renystyles.com-1.jpg",
                    "Ade and Johnson Suites",
                    "Mangu, Plateau"
                )
            )
            list.add(
                Tailor(
                    8,
                    "https://www.mynativefashion.com/wp-content/uploads/2020/10/Nigerian-Fashion-Dresses-16-1024x1024.jpg",
                    "Simpson's place",
                    "Garki, Abuja"
                )
            )
            return list
        }
    }
}
