package com.app.newsapp.feature.movie.presentation.ui.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.app.newsapp.R
import com.app.newsapp.feature.movie.presentation.ui.home.HomeViewModel

@Composable
fun DetailScreen(
    imageUrl: String,
    viewModel: HomeViewModel = hiltViewModel(),
    onNavClick: () -> Unit
) {
    Column {
        DetailsTopAppBar(4.dp, onNavClick)
        Box(modifier = Modifier.fillMaxSize()) {

        }
    }
}

@Composable
fun DetailsTopAppBar(
    elevation: Dp,
    onNavClick: () -> Unit
) {
    val title = stringResource(id = R.string.pg__details)
    TopAppBar(
        title = {
            Text(title)
        },
        navigationIcon = {
            IconButton(onClick = onNavClick) {
                Icon(
                    painter = painterResource(R.drawable.ic_back),
                    contentDescription = stringResource(R.string.pg__details_desc),
                    tint = MaterialTheme.colors.primary
                )
            }

        },
        backgroundColor = MaterialTheme.colors.surface,
        elevation = elevation,
        modifier = Modifier.fillMaxWidth()
    )
}
