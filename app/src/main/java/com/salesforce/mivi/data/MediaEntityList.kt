package com.salesforce.mivi.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MediaEntityList(
    @SerialName(SEARCH_RES_ARRAY) val mediaEntities: List<MediaEntity>
) {
    companion object {
        private const val SEARCH_RES_ARRAY = "Search"
    }
}