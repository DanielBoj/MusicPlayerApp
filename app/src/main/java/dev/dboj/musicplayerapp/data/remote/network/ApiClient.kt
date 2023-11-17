package dev.dboj.musicplayerapp.data.remote.network

import dev.dboj.musicplayerapp.data.remote.api.IApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private const val BASE_URL = "https://deezerdevs-deezer.p.rapidapi.com/"

    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: IApi by lazy {
        retrofit.create(IApi::class.java)
    }
}