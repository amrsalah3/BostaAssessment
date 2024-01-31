package com.bosta.assessment.amrsalah.ui

import android.content.res.Configuration
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.bosta.assessment.amrsalah.R
import com.bosta.assessment.amrsalah.ui.theme.BostaAssessmentTheme

@Composable
fun EmptyContent(
    @StringRes messageResId: Int,
    modifier: Modifier = Modifier,
) {
    Box(modifier.fillMaxSize()) {
        Text(
            text = stringResource(messageResId),
            color = Color.Gray,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Preview(device = "id:pixel_2", showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun EmptyContentPreview() {
    BostaAssessmentTheme {
        Surface {
            EmptyContent(R.string.empty_content)
        }
    }
}