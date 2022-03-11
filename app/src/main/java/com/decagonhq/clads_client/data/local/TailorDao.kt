package com.decagonhq.clads_client.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.decagonhq.clads_client.data.model.Tailor

@Dao
interface TailorDao {

    @Insert(onConflict =  OnConflictStrategy.REPLACE)
    //upsert does update if post exists or insert new
    suspend fun upsert (tailor: Tailor)

    @Query("SELECT * FROM `favourite artisans`")
    fun getAllFavourites(): LiveData<List<Tailor>>

    @Query("SELECT * FROM `favourite artisans` WHERE id LIKE :id LIMIT 1")
    fun isFavourite(id:Int):LiveData<List<Tailor>>

    @Delete
    suspend fun deleteArtisan(tailor: Tailor)
}