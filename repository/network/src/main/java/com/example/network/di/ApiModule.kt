package com.example.network.di

import co.infinum.retromock.Retromock
import com.example.network.list.ListService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    @Singleton
    @Provides
    fun provideListService(retromock: Retromock): ListService =
        retromock.create(ListService::class.java)

    @Singleton
    @Provides
    fun provideOkHttp(): OkHttpClient {
        val builder = OkHttpClient.Builder()

        return builder.build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retromock {
        val retrofit = Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://localhost:8080/")
            .addConverterFactory(
                Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                    allowStructuredMapKeys = true
                    prettyPrint = true
                    coerceInputValues = true
                }.asConverterFactory(
                    "application/json".toMediaType()
                ),
            ).build()

        return Retromock.Builder().retrofit(retrofit).build()
    }
}