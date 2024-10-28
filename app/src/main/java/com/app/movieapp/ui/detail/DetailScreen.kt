package com.app.movieapp.ui.detail

import androidx.compose.foundation.Image
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
import coil.compose.rememberImagePainter
import com.app.movieapp.R

@Composable
fun DetailScreen(
    imageUrl: String,
    onNavClick: () -> Unit
) {

    Column {
        DetailsTopAppBar(4.dp, onNavClick)
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = rememberImagePainter(data = imageUrl),
                contentDescription = null,
                modifier = Modifier.fillMaxSize()
            )
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
