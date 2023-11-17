package dev.dboj.musicplayerapp.services

import android.util.Log
import dev.dboj.musicplayerapp.data.remote.api.IApi
import dev.dboj.musicplayerapp.data.remote.model.MusicData
import kotlinx.coroutines.suspendCancellableCoroutine
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiServiceImpl(
    private val api: IApi
): IApiService {
    // Singleton
    companion object {
        @Volatile
        private var instance: ApiServiceImpl? = null

        fun getInstance(api: IApi): ApiServiceImpl =
            instance ?: synchronized(this) {
                instance ?: ApiServiceImpl(api).also { instance = it }
            }
    }
    override suspend fun fetchData(query: String): List<MusicData> {
        return suspendCancellableCoroutine { continuation ->
            api.getData(query).enqueue(object: Callback<List<MusicData>?> {
                override fun onResponse(call: Call<List<MusicData>?>, response: Response<List<MusicData>?>) {
                    if (response.isSuccessful) response.body()
                        ?.let { continuation.resumeWith(Result.success(it)) }
                        ?: continuation.resumeWith(Result.success(emptyList()))
                }

                override fun onFailure(call: Call<List<MusicData>?>, t: Throwable) {
                    Log.e("ApiService", "Error: ${t.message}")
                    continuation.resumeWith(Result.failure(t))
                }
            })

            continuation.invokeOnCancellation {
                it?.let { Log.e("ApiService", "Error: ${it.message}") }
                if (!continuation.isCompleted) continuation.cancel()
            }
        }
    }
}