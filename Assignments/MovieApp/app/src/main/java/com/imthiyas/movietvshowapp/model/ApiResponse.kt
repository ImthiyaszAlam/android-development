package com.imthiyas.movietvshowapp.model

data class ApiResponse<T>(
    val results: List<T>
)
