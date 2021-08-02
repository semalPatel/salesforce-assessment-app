package com.salesforce.mivi.network

import com.salesforce.mivi.data.DetailedMediaEntity
import com.salesforce.mivi.data.SearchMediaEntity
import com.salesforce.mivi.data.SearchMediaEntityList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ContentService {

    @GET(".")
    suspend fun getContentBySearch(
        @Query(API_KEY) apiKey: String,
        @Query(TITLE) searchTitle: String
    ): Response<SearchMediaEntityList>

    @GET(".")
    suspend fun getContentById(
        @Query(API_KEY) apiKey: String,
        @Query(ID) id: String
    ): Response<DetailedMediaEntity>

    companion object {
        private const val TITLE = "s"
        private const val API_KEY = "apikey"
        private const val ID = "i"
    }
}