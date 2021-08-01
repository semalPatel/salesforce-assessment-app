package com.salesforce.mivi.network

import com.salesforce.mivi.data.MediaEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    @GET(".")
    suspend fun getMoviesBySearch(@Query(TITLE) searchTitle: String): Response<List<MediaEntity>>

    @GET(".")
    suspend fun getMovieById(
        @Query(API_KEY) apiKey: String,
        @Query(ID) id: String
    ): Response<MediaEntity>

    companion object {
        private const val TITLE = "s"
        private const val API_KEY = "apikey"
        private const val ID = "i"
    }
}