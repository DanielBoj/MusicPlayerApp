package dev.dboj.musicplayerapp.services

import dev.dboj.musicplayerapp.data.remote.model.MusicData

interface IApiService {
    suspend fun fetchData(query: String): List<MusicData>
}