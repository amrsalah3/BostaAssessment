package com.bosta.assessment.amrsalah

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.bosta.assessment.amrsalah.BostaAssessmentDestinationArgs.ALBUM_ID_ARG
import com.bosta.assessment.amrsalah.BostaAssessmentDestinations.ALBUM_ROUTE
import com.bosta.assessment.amrsalah.BostaAssessmentDestinations.PROFILE_ROUTE
import com.bosta.assessment.amrsalah.BostaAssessmentScreens.ALBUM_SCREEN
import com.bosta.assessment.amrsalah.BostaAssessmentScreens.PROFILE_SCREEN
import com.bosta.assessment.amrsalah.ui.album.AlbumRoute
import com.bosta.assessment.amrsalah.ui.profile.ProfileRoute

@Composable
fun BostaAssessmentNavGraph(
    navController: NavHostController = rememberNavController(),
    startDestination: String = PROFILE_SCREEN
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(route = PROFILE_ROUTE) {
            ProfileRoute(
                onAlbumClick = { album ->
                    navController.navigate("$ALBUM_SCREEN/${album.id}")
                }
            )
        }

        val albumIdArg = navArgument(ALBUM_ID_ARG) {
            type = NavType.IntType
            nullable = false
        }
        composable(
            route = ALBUM_ROUTE,
            arguments = listOf(albumIdArg),
        ) {
            AlbumRoute()
        }

    }
}

object BostaAssessmentScreens {
    const val PROFILE_SCREEN = "profile"
    const val ALBUM_SCREEN = "album"
}

object BostaAssessmentDestinations {
    const val PROFILE_ROUTE = PROFILE_SCREEN
    const val ALBUM_ROUTE = "$ALBUM_SCREEN/{$ALBUM_ID_ARG}"
}

object BostaAssessmentDestinationArgs {
    const val ALBUM_ID_ARG = "albumId"
}
