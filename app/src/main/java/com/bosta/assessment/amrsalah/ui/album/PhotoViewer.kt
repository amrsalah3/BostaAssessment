package com.bosta.assessment.amrsalah.ui.album

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.bosta.assessment.amrsalah.R
import com.bosta.assessment.amrsalah.ui.LoadingContent
import com.bosta.assessment.amrsalah.ui.theme.BostaAssessmentTheme
import net.engawapg.lib.zoomable.rememberZoomState
import net.engawapg.lib.zoomable.zoomable


@Composable
fun PhotoViewer(photoUrlState: MutableState<String?>) {
    var isLoading by rememberSaveable { mutableStateOf(false) }
    val context = LocalContext.current
    var imageUri: Uri? = null
    Dialog(
        onDismissRequest = { photoUrlState.value = null },
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Box(
            Modifier
                .background(Color.Black)
                .fillMaxSize()
        ) {
            AsyncImage(
                model = ImageRequest.Builder(context)
                    .data(photoUrlState.value)
                    .build(),
                onLoading = { isLoading = true },
                onSuccess = {
                    isLoading = false
                    imageUri = PhotoUtils.getUriFromDrawable(context, it.result.drawable)
                },
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxSize()
                    .zoomable(rememberZoomState())
            )

            if (isLoading) LoadingContent()

            IconButton(
                onClick = {
                    imageUri?.let { PhotoUtils.sharePhoto(context, it) }
                },
                content = {
                    Icon(
                        imageVector = Icons.Default.Share,
                        tint = Color.White,
                        contentDescription = stringResource(R.string.content_desc_share_photo)
                    )
                },
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(12.dp)
            )
        }
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
