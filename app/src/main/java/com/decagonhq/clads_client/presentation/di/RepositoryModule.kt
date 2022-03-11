package com.decagonhq.clads_client.presentation.di

import com.decagonhq.clads_client.data.local.FavouritesDatabase
import com.decagonhq.clads_client.data.repository.FavouritesRepository
import com.decagonhq.clads_client.data.repository.ProfileRepository
import com.decagonhq.clads_client.network.ClientAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

object RepositoryModule {

    @Singleton
    @Provides
    fun provideFavouritesRepository(
        favouritesDatabase: FavouritesDatabase
    ):FavouritesRepository{
        return FavouritesRepository(favouritesDatabase)
    }

    @Provides
    @Singleton
    fun provideProfileRepository(
        api: ClientAPI,
        favouritesDatabase: FavouritesDatabase
    ): ProfileRepository{
        return ProfileRepository(api,favouritesDatabase)
    }
}