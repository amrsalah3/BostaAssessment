package com.bosta.assessment.amrsalah.ui.album

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.bosta.assessment.amrsalah.R
import com.bosta.assessment.amrsalah.domain.Result
import com.bosta.assessment.amrsalah.domain.album.model.Album
import com.bosta.assessment.amrsalah.domain.album.model.Photo
import com.bosta.assessment.amrsalah.ui.EmptyContent
import com.bosta.assessment.amrsalah.ui.LoadingContent
import com.bosta.assessment.amrsalah.ui.theme.BostaAssessmentTheme

@Composable
fun AlbumRoute(viewModel: AlbumViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    when (val result = uiState) {
        Result.Loading -> LoadingContent()

        is Result.Success -> AlbumScreen(
            album = result.data,
            onSearch = viewModel::filterPhotosByTitle,
        )

        is Result.Failure -> EmptyContent(R.string.error_loading_album)
    }
}

@Composable
fun AlbumScreen(album: Album, onSearch: (String) -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = album.title,
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .fillMaxWidth()
        )

        Divider(thickness = Dp.Hairline)

        SearchBar(
            onSearch = onSearch,
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .fillMaxWidth()
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier.fillMaxSize()
        ) {
            items(album.filteredPhotos.size) { index ->
                NetworkImage(
                    photoUrl = album.filteredPhotos[index].thumbnailUrl,
                    modifier = Modifier.aspectRatio(1.4f)
                )
            }
        }
    }
}

@Composable
fun SearchBar(onSearch: (String) -> Unit, modifier: Modifier = Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .height(38.dp)
            .background(
                color = Color.Gray.copy(alpha = 0.2f),
                shape = ShapeDefaults.Small
            )
    ) {
        Icon(
            imageVector = Icons.Default.Search,
            tint = Color.Gray,
            contentDescription = null,
            modifier = Modifier.padding(start = 8.dp)
        )

        var query by rememberSaveable { mutableStateOf("") }

        BasicTextField(
            value = query,
            onValueChange = {
                query = it
                onSearch(it)
            },
            textStyle = TextStyle(color = MaterialTheme.colorScheme.onBackground),
            singleLine = true,
            cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
            decorationBox = { innerTextField ->
                Box {
                    if (query.isEmpty()) {
                        Text(text = "Search in images..", color = Color.Gray)
                    }
                    innerTextField()
                }
            },
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .fillMaxWidth()
        )
    }
}

@Composable
fun NetworkImage(photoUrl: String, modifier: Modifier = Modifier) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(photoUrl)
            .crossfade(500)
            .build(),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = modifier
    )
}

@Preview
@Composable
fun AlbumScreenPreview() {
    BostaAssessmentTheme {
        Surface {
            AlbumScreen(
                album = Album(
                    id = 0,
                    title = "quidem molestiae enim",
                    photos = List(20) {
                        Photo(
                            id = 0,
                            title = "photo title",
                            url = "https://via.placeholder.com/600/92c952",
                            thumbnailUrl = "https://via.placeholder.com/150/92c952"
                        )
                    }
                ),
                onSearch = {}
            )
        }
    }
}
