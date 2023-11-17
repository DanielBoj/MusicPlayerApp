package dev.dboj.musicplayerapp.data.remote.api

import dev.dboj.musicplayerapp.data.remote.model.MusicData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface IApi {
    @Headers("X-RapidAPI-Key 756633c6e7msh0be00ce7e5fb25bp159dc1jsnc7b477c87b11",
        "X-RapidAPI-Hostdeezerdevs-deezer.p.rapidapi.com")
    @GET("search")
    fun getData(@Query("q") query: String): Call<List<MusicData>>
}