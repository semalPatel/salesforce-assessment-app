package com.salesforce.mivi.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.salesforce.mivi.network.AuthInterceptor
import com.salesforce.mivi.network.ContentService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun provideBaseUrl() = "https://www.omdbapi.com/"

    @Provides
    fun provideAuthInterceptor() = AuthInterceptor()

    @Provides
    @Singleton
    fun provideOkHttpClient(authInterceptor: AuthInterceptor) = OkHttpClient
        .Builder()
        //.addInterceptor(authInterceptor)
        .build()

    @Provides
    @Singleton
    fun provideMovieService(
        okHttpClient: OkHttpClient,
        baseUrl: String
    ) = createService(okHttpClient, provideBaseUrl(), ContentService::class.java)

    private fun createRetrofit(okHttpClient: OkHttpClient, baseUrl: String): Retrofit {
        val contentType = MediaType.get("application/json")
        val json = Json { ignoreUnknownKeys = true }
        return Retrofit.Builder()
            .addConverterFactory(json.asConverterFactory(contentType))
            .client(okHttpClient)
            .baseUrl(baseUrl)
            .build()
    }

    private fun <T> createService(okHttpClient: OkHttpClient,
                                  baseUrl: String,
                                  clazz: Class<T>): T {
        return createRetrofit(okHttpClient, baseUrl).create(clazz)
    }
}
