package dev.dboj.musicplayerapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Observer
import dev.dboj.musicplayerapp.data.remote.api.IApi
import dev.dboj.musicplayerapp.data.remote.model.MusicData
import dev.dboj.musicplayerapp.data.remote.network.ApiClient
import dev.dboj.musicplayerapp.ui.theme.MusicPlayerAppTheme
import dev.dboj.musicplayerapp.viewmodel.MainActivityViewModel

class MainActivity : ComponentActivity() {
    private val controller: MainActivityViewModel = MainActivityViewModel()
    private var data: List<MusicData> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        controller.musicData.observe(this) { musicData ->
            data = musicData
        }
        controller.fetchMusicData("lana del rey")
        setContent {
            MusicPlayerAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Greeting(data)
                }
            }
        }
    }
}

@Composable
fun Greeting(data: List<MusicData>) {
    Log.w("MainActivity", "Data: $data")
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MusicPlayerAppTheme {
        Greeting(data = emptyList())
    }
}