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

        fun getDefaultInstance(): MediaEntity = MediaEntity(
            imdbId = "tt0372784",
            title = "Batman Begins",
            year = "2005",
            type = EntityType.MOVIE,
            posterUrl = "https://m.media-amazon.com/images/M/MV5BOTY4YjI2N2MtYmFlMC00ZjcyLTg3YjEtMDQyM2ZjYzQ5YWFkXkEyXkFqcGdeQXVyMTQxNzMzNDI@._V1_SX300.jpg"
        )
    }
}

@Serializable
enum class EntityType {
    @SerialName("movie") MOVIE,
    @SerialName("series") SERIES
}