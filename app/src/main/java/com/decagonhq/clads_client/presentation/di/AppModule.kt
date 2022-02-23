package com.decagonhq.clads_client.presentation.di

import com.decagonhq.clads_client.presentation.network.CladsClientApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

// Dependency Injection Module
object AppModule {
    @Provides
    @Singleton
    fun provideLogger(): HttpLoggingInterceptor{
        return HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
    }

    @Provides
    @Singleton
    fun provideClient (logger: HttpLoggingInterceptor): OkHttpClient{
        return OkHttpClient.Builder().
        addInterceptor(logger)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl("")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideClientAPI(retrofit: Retrofit): CladsClientApi =
        retrofit.create(CladsClientApi::class.java)
}