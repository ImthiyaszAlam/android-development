package com.imthiyas.movietvshowapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.imthiyas.movietvshowapp.ui.screens.DetailsScreen
import com.imthiyas.movietvshowapp.ui.screens.HomeScreen
import com.imthiyas.movietvshowapp.viewmodel.MovieViewModel
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val viewModel: MovieViewModel = koinViewModel()
            NavHost(navController, startDestination = "home") {
                composable("home") {
                    HomeScreen(viewModel, navController)
                }
            }
            NavHost(navController, startDestination = "home") {
                composable("home") { HomeScreen(viewModel, navController) }
                composable("details/{title}/{description}/{releaseDate}/{posterUrl}") { backStackEntry ->
                    val args = backStackEntry.arguments!!
                    DetailsScreen(
                        navController,
                        args.getString("title")!!,
                        args.getString("description")!!,
                        args.getString("releaseDate")!!,
                        args.getString("posterUrl")!!
                    )
                }
            }
        }
    }
}