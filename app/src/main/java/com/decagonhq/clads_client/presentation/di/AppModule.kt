package com.decagonhq.clads_client.presentation.di

import com.decagonhq.clads_client.data.repository.AuthRepository
import com.decagonhq.clads_client.data.repsitory.ProfileRepository
import com.decagonhq.clads_client.presentation.network.ClientAPI
import com.decagonhq.clads_client.presentation.network.NetworkConstants.Companion.BASE_URL
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
    fun provideLogger(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
    }

    @Provides
    @Singleton
    fun provideClient(logger: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(logger)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideClientAPI(retrofit: Retrofit): ClientAPI =
        retrofit.create(ClientAPI::class.java)

    @Provides
    @Singleton
    fun provideProfileRepository(api: ClientAPI): ProfileRepository =
        ProfileRepository(api)

    fun provideAuthRepository(api: ClientAPI): AuthRepository =
        AuthRepository(api)
}
