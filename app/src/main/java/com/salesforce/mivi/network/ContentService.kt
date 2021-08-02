package com.salesforce.mivi.network

import com.salesforce.mivi.data.MediaEntity
import com.salesforce.mivi.data.MediaEntityList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ContentService {

    @GET(".")
    suspend fun getContentBySearch(
        @Query(API_KEY) apiKey: String,
        @Query(TITLE) searchTitle: String
    ): Response<MediaEntityList>

    @GET(".")
    suspend fun getContentById(
        @Query(API_KEY) apiKey: String,
        @Query(ID) id: String
    ): Response<MediaEntity>

    companion object {
        private const val TITLE = "s"
        private const val API_KEY = "apikey"
        private const val ID = "i"
    }
}