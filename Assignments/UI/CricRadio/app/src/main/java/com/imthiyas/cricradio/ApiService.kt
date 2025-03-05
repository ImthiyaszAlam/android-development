package com.example.cricradio.data.remote

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

private const val BASE_URL = "http://3.6.243.12:5001"
private const val AUTH_HEADER = "Basic Y3JpY2tldFJhZGlvOmNyaWNrZXRAJCUjUmFkaW8xMjM="

@Serializable
data class MiniScorecardResponse(
    val teamA: String,
    val teamB: String,
    val runs: Int,
    val wickets: Int,
    val overs: Double
)

class ApiService {
    private val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(Json { ignoreUnknownKeys = true })  // Use the new JSON serialization
        }
        defaultRequest {
            header("Authorization", AUTH_HEADER)
        }
    }

    suspend fun getMiniScorecard(): MiniScorecardResponse {
        return client.get("$BASE_URL/api/v2/match/mini-match-card") {
            parameter("key", "SA_vs_SL_2024-12-05_1732276435.300452")
        }.body()
    }
}
