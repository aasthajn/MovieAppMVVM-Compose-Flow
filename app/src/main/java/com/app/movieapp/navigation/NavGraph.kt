package com.app.movieapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.app.movieapp.ui.detail.DetailScreen
import com.app.movieapp.ui.home.HomeScreen
import com.app.movieapp.ui.home.HomeViewModel
import com.app.movieapp.ui.home.SearchScreen
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun NavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = NavScreens.Home.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(NavScreens.Home.route) {
            val viewModel = hiltViewModel<HomeViewModel>()
            HomeScreen(modifier,viewModel) {
                val url = URLEncoder.encode(it, StandardCharsets.UTF_8.toString())
                navController.navigate("detail/$url")
                /*navController.run { navigate("${NavScreens.Detail.route}/$it")*/
            }
        }

        composable(
            "${NavScreens.Detail.route}/{${GalleryNavArgs.IMAGE_URL}}",
            arguments = listOf(navArgument(GalleryNavArgs.IMAGE_URL) { type = NavType.StringType }),
            deepLinks = listOf(navDeepLink { uriPattern = "android-app://androidx.navigation/detail/{${GalleryNavArgs.IMAGE_URL}}" })
        ) { backStackEntry ->
            val imageUrl = backStackEntry.arguments?.getString(GalleryNavArgs.IMAGE_URL)
            DetailScreen(imageUrl = imageUrl ?: "") {
                navController.popBackStack()
            }
        }

        composable(NavScreens.Search.route) {
            val viewModel = hiltViewModel<HomeViewModel>()
            SearchScreen(viewModel,
                onSearchQueryChange = {viewModel.onSearchQueryChange(it)},
                onSelected = {
                    val url = URLEncoder.encode(it.posterUrl, StandardCharsets.UTF_8.toString())
                    navController.navigate("detail/$url")})

                   // navController.run { navigate("${NavScreens.Detail.route}/${it.posterUrl}") }})

            }

    }

}
