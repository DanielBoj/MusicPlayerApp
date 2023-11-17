package dev.dboj.musicplayerapp.data.remote.model

data class MusicData(
    val `data`: List<Data>,
    val next: String,
    val total: Int
)