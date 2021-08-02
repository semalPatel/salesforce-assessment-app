package com.salesforce.mivi.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DetailedMediaEntity(
    @SerialName(IMDB_ID) val imdbId: String,
    @SerialName(TITLE) val title: String,
    @SerialName(YEAR) val year: String,
    @SerialName(TYPE) val type: SearchMediaEntity.EntityType,
    @SerialName(POSTER) val posterUrl: String,
    @SerialName(PLOT) val plot: String,
    @SerialName(DIRECTOR) val director: String,
    @SerialName(ACTORS) val actors: String,
    @SerialName(WRITER) val writer: String,
    @SerialName(LANGUAGE) val language: String,
    @SerialName(RELEASED) val released: String,
    @SerialName(RUNTIME) val runtime: String,
    @SerialName(GENRE) val genre: String,

    ) {
    companion object {
        private const val IMDB_ID = "imdbID"
        private const val TITLE = "Title"
        private const val YEAR = "Year"
        private const val TYPE = "Type"
        private const val POSTER = "Poster"
        private const val PLOT = "Plot"
        private const val DIRECTOR = "Director"
        private const val ACTORS = "Actors"
        private const val WRITER = "Writer"
        private const val LANGUAGE = "Language"
        private const val RELEASED = "Released"
        private const val RUNTIME = "Runtime"
        private const val GENRE = "Genre"
    }
}
