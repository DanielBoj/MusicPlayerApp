package dev.dboj.musicplayerapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.dboj.musicplayerapp.data.remote.api.IApi
import dev.dboj.musicplayerapp.data.remote.model.MusicData
import dev.dboj.musicplayerapp.data.remote.network.ApiClient
import dev.dboj.musicplayerapp.services.ApiServiceImpl
import dev.dboj.musicplayerapp.services.IApiService
import kotlinx.coroutines.launch

class MainActivityViewModel(
    private val api: IApi = ApiClient.api
): ViewModel() {
    private val apiService: IApiService = ApiServiceImpl.getInstance(api)
    private val _musicData: MutableLiveData<List<MusicData>> = MutableLiveData()
    val musicData: LiveData<List<MusicData>>
        get() = _musicData

    fun fetchMusicData(query: String) {
        viewModelScope.launch {
            try {
                _musicData.value = apiService.fetchData(query)
            } catch (e: Exception) {
                Log.e("MainActivityViewModel", "Error: ${e.message}")
                _musicData.value = emptyList()
            }
        }
    }
}