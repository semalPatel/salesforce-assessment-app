package com.salesforce.mivi.viewmodel

import com.salesforce.mivi.BuildConfig
import com.salesforce.mivi.data.DetailedMediaEntity
import com.salesforce.mivi.data.Result
import com.salesforce.mivi.data.SearchMediaEntityList
import com.salesforce.mivi.network.ContentService
import com.salesforce.mivi.util.apiCall
import javax.inject.Inject

class ContentRepository @Inject constructor(
    private val contentService: ContentService
) {
    suspend fun getContentList(query: String): Result<SearchMediaEntityList> {
        return apiCall {
            contentService.getContentBySearch(
                apiKey = BuildConfig.API_KEY_VALUE,
                searchTitle = query
            )
        }
    }

    suspend fun getContentById(id: String): Result<DetailedMediaEntity> {
        return apiCall {
            contentService.getContentById(
                apiKey = BuildConfig.API_KEY_VALUE,
                id = id
            )
        }
    }
}