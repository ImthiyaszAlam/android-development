package com.imthiyas.movietvshowapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.imthiyas.movietvshowapp.ui.components.MovieItem
import com.imthiyas.movietvshowapp.ui.components.ShimmerEffect
import com.imthiyas.movietvshowapp.viewmodel.MovieViewModel

@Composable
fun HomeScreen(viewModel: MovieViewModel, navController: NavController) {
    val movies by viewModel.movies.collectAsState()
    val tvShows by viewModel.tvShows.collectAsState()
    var isMovieSelected by remember { mutableStateOf(true) }

    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(onClick = { isMovieSelected = true }) {
                Text("Movies")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = { isMovieSelected = false }) {
                Text("TV Shows")
            }
        }
        if (movies.isEmpty() && tvShows.isEmpty()) {
            ShimmerEffect()
        }else{
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(if (isMovieSelected) movies else tvShows) { item ->
                    MovieItem(item, navController, isMovieSelected)
                }
            }
        }
    }
}