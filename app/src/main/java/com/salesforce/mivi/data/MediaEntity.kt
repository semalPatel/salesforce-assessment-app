package com.salesforce.mivi.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MediaEntity(
    @SerialName(IMDB_ID) val imdbId: String,
    @SerialName(TITLE) val title: String,
    @SerialName(YEAR) val year: String,
    @SerialName(TYPE) val type: EntityType,
    @SerialName(POSTER) val posterUrl: String
) {
    companion object {
        private const val IMDB_ID = "imdbID"
        private const val TITLE = "Title"
        private const val YEAR = "Year"
        private const val TYPE = "Type"
        private const val POSTER = "Poster"
    }
}

@Serializable
enum class EntityType {
    @SerialName("movie") MOVIE,
    @SerialName("series") SERIES
}