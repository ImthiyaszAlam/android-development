package com.imthiyas.movietvshowapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.imthiyas.movietvshowapp.model.Movie
import com.imthiyas.movietvshowapp.model.TVShow

@Composable
fun MovieItem(item: Any, navController: NavController, isMovie: Boolean) {
    val title: String = when (item) {
        is Movie -> item.title
        is TVShow -> item.name
        else -> "Untitled"
    }

    val description: String = when (item) {
        is Movie -> item.overview
        is TVShow -> item.overview
        else -> "No description available"
    }

    val releaseDate: String = when (item) {
        is Movie -> item.release_date
        is TVShow -> item.first_air_date
        else -> "Unknown release date"
    }

    val posterUrl: String = when (item) {
        is Movie -> item.poster_url
        is TVShow -> item.poster_url
        else -> ""
    }


    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                navController.navigate("details/$title/$description/$releaseDate/$posterUrl")
            },
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(modifier = Modifier.padding(8.dp)) {
            Image(
                painter = rememberImagePainter(posterUrl),
                contentDescription = title,
                modifier = Modifier.size(100.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(text = title, style = MaterialTheme.typography.headlineSmall)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "Release Date: $releaseDate", style = MaterialTheme.typography.bodyMedium)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = description.take(100) + "...", style = MaterialTheme.typography.bodySmall)
            }
        }
    }

}