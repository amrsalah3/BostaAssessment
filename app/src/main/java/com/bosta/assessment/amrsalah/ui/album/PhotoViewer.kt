package com.bosta.assessment.amrsalah.ui.album

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.bosta.assessment.amrsalah.ui.theme.BostaAssessmentTheme
import net.engawapg.lib.zoomable.rememberZoomState
import net.engawapg.lib.zoomable.zoomable

@Composable
fun PhotoViewer(photoUrlState: MutableState<String?>) {
    Dialog(
        onDismissRequest = { photoUrlState.value = null },
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(photoUrlState.value)
                .build(),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .background(Color.Black)
                .fillMaxSize()
                .zoomable(rememberZoomState())
        )
    }
}

@Preview
@Composable
fun PhotoViewerPreview() {
    BostaAssessmentTheme {
        Surface {
            PhotoViewer(remember { mutableStateOf(null) })
        }
    }
}
