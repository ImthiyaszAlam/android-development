package com.imthiyas.fcm

import retrofit2.http.Body
import retrofit2.http.GET

interface FCMApiService {

    @GET("/send")
    suspend fun sendMessage(
        @Body body: SendMessageDto
    )


    @GET("/broad")
    suspend fun broadCast(
        @Body body: SendMessageDto
    )
}