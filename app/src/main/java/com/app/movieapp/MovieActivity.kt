package com.app.movieapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.app.movieapp.coreui.BottomNavigationBar
import com.app.movieapp.navigation.NavGraph
import com.app.movieapp.navigation.NavScreens
import com.app.movieapp.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            AppTheme{
                val navController = rememberNavController()
                // A surface container using the 'background' color from the theme
                val screens = listOf(
                    NavScreens.Home,
                    NavScreens.Search
                )
                Surface(
                    modifier = Modifier
                        .fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ){
                    Scaffold(
                        bottomBar = {
                            BottomNavigationBar(navController,screens)
                        }
                    ) {
                        NavGraph(navController = navController)
                    }
                }
            }
        }
    }
}
