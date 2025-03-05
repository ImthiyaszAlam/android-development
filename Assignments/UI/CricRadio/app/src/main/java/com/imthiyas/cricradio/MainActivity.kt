package com.imthiyas.cricradio

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.cricradio.ui.viewmodel.MiniScorecardViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MiniScorecardScreen()
        }
    }
}



@Composable
fun MiniScorecardScreen(
    viewModel: MiniScorecardViewModel = hiltViewModel()
) {
    val scorecardState = viewModel.miniScorecard.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        scorecardState.value?.let { scorecard ->
            Text(
                text = "${scorecard.teamA} vs ${scorecard.teamB}",
                style = MaterialTheme.typography.headlineSmall
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Runs: ${scorecard.runs}/${scorecard.wickets}",
                style = MaterialTheme.typography.headlineSmall
            )
            Text(text = "Overs: ${scorecard.overs}", style = MaterialTheme.typography.bodyLarge)
        } ?: run {
            CircularProgressIndicator()
        }
    }
}


