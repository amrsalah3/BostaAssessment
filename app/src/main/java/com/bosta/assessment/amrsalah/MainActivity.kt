package com.bosta.assessment.amrsalah

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.bosta.assessment.amrsalah.ui.theme.BostaAssessmentTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BostaAssessmentTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BostaAssessmentNavGraph()
                    /*AlbumScreen(
                        albumTitle = "quidem molestiae enim",
                        photos = List(30) {
                            Photo(
                                id = 0,
                                title = "photo title",
                                url = "https://via.placeholder.com/600/92c952",
                                thumbnailUrl = "https://via.placeholder.com/150/92c952"
                            )
                        }
                    )*/
                }
            }
        }
    }
}
