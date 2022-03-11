package com.decagonhq.clads_client.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.decagonhq.clads_client.data.model.Tailor

@Database(
    entities = [Tailor::class],
    version = 1,
    exportSchema = false
)
abstract class FavouritesDatabase:RoomDatabase() {

    abstract fun getTailorDao():TailorDao

    companion object{
      var DATABASE_NAME:String = "favourites_db"
    }
}