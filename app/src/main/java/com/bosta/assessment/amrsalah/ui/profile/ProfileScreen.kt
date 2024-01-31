package com.bosta.assessment.amrsalah.ui.profile

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bosta.assessment.amrsalah.R
import com.bosta.assessment.amrsalah.domain.Result
import com.bosta.assessment.amrsalah.domain.album.model.Album
import com.bosta.assessment.amrsalah.domain.profile.model.Profile
import com.bosta.assessment.amrsalah.ui.EmptyContent
import com.bosta.assessment.amrsalah.ui.LoadingContent
import com.bosta.assessment.amrsalah.ui.theme.BostaAssessmentTheme

@Composable
fun ProfileRoute(
    viewModel: ProfileViewModel = hiltViewModel(),
    onAlbumClick: (Album) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    when (val result = uiState) {
        Result.Loading -> LoadingContent()

        is Result.Success -> ProfileScreen(
            profile = result.data,
            onAlbumClick = onAlbumClick
        )

        is Result.Failure -> EmptyContent(R.string.error_loading_profile)
    }
}

@Composable
fun ProfileScreen(profile: Profile, onAlbumClick: (Album) -> Unit) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp)
    ) {
        item {
            Text(
                text = "Profile",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }

        item {
            Text(
                text = profile.username,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }

        item {
            Text(profile.address)
        }

        item {
            Text(
                text = "My Albums",
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(top = 12.dp, bottom = 8.dp)
            )
        }

        items(profile.albums.size) { index ->
            val album = profile.albums[index]
            Text(
                text = album.title,
                modifier = Modifier
                    .padding(vertical = 12.dp)
                    .fillMaxWidth()
                    .clickable { onAlbumClick(album) }
            )

            if (index != profile.albums.lastIndex) {
                Divider(thickness = Dp.Hairline)
            }
        }

    }
}

@Preview
@Composable
fun ProfileScreenPreview() {
    BostaAssessmentTheme {
        Surface {
            ProfileScreen(
                Profile(
                    id = 0,
                    username = "Leanne Graham",
                    address = "Kulas Light, Apt. 556, Gwenborough, 92998-3874",
                    albums = List(50) { i ->
                        Album(
                            id = i,
                            title = List(20) { ('a'..'z').random() }.joinToString(separator = ""),
                            photos = emptyList()
                        )
                    }
                ),
                onAlbumClick = {}
            )
        }
    }
}
