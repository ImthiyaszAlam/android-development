package com.example.cricradio.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cricradio.data.remote.ApiService
import com.example.cricradio.data.remote.MiniScorecardResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MiniScorecardViewModel @Inject constructor(
    private val apiService: ApiService
) : ViewModel() {

    private val _miniScorecard = MutableStateFlow<MiniScorecardResponse?>(null)
    val miniScorecard: StateFlow<MiniScorecardResponse?> = _miniScorecard

    init {
        fetchMiniScorecard()
    }

    private fun fetchMiniScorecard() {
        viewModelScope.launch {
            try {
                Log.d("MiniScorecardViewModel", "Fetching data...") // Debug log
                val response = apiService.getMiniScorecard()
                val responseBody = response.bodyAsText()
                println("API Response: $responseBody")
                Log.d("MiniScorecardViewModel", "Response: $response")
                _miniScorecard.value = response
            } catch (e: Exception) {
                Log.e("MiniScorecardViewModel", "Error: ${e.message}", e)
                e.printStackTrace()
            }
        }
    }
}
