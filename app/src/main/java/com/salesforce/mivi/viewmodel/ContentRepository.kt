package com.salesforce.mivi.viewmodel

import android.util.Log
import com.salesforce.mivi.BuildConfig
import com.salesforce.mivi.Result
import com.salesforce.mivi.apiCall
import com.salesforce.mivi.data.MediaEntity
import com.salesforce.mivi.data.MediaEntityList
import com.salesforce.mivi.network.ContentService
import javax.inject.Inject

class ContentRepository @Inject constructor(
    private val contentService: ContentService
) {
    suspend fun getContentList(query: String): Result<MediaEntityList> {
        return apiCall {
            contentService.getContentBySearch(
                apiKey = BuildConfig.API_KEY_VALUE,
                searchTitle = query
            )
        }
    }

    suspend fun getContentById(id: String): Result<MediaEntity> {
        return apiCall {
            contentService.getContentById(
                apiKey = BuildConfig.API_KEY_VALUE,
                id = id
            )
        }
    }
}