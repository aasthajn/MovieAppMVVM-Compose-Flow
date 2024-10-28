package com.app.movieapp.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Details
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector


sealed class NavScreens(val title: String,  val route: String,val icon: ImageVector) {
    object Home : NavScreens("Home", "home",Icons.Filled.Home)
    object Detail : NavScreens("Detail", "detail",Icons.Filled.Details)

    object Search : NavScreens("Search","search",Icons.Filled.Search)
}

val screens = listOf(
    NavScreens.Home,
    NavScreens.Detail
)

object GalleryNavArgs{
    const val IMAGE_URL = "imageUrl"
}
