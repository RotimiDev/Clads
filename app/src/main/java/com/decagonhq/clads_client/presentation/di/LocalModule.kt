package com.decagonhq.clads_client.presentation.di

import android.content.Context
import androidx.room.Room
import com.decagonhq.clads_client.data.local.FavouritesDatabase
import com.decagonhq.clads_client.data.local.TailorDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

object LocalModule {

    @Singleton
    @Provides
    fun providesFavouritesDataBase(@ApplicationContext context: Context): FavouritesDatabase {
        return Room.databaseBuilder(context,
            FavouritesDatabase::class.java,
            FavouritesDatabase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun providesTailorDao(favouritesDatabase: FavouritesDatabase): TailorDao {
        return favouritesDatabase.getTailorDao()
    }
}