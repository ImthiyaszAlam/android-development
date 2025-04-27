package com.imthiyas.fcm

import androidx.lifecycle.ViewModel
import retrofit2.Retrofit

class ChatViewModel:ViewModel() {

    private val api:FCMApiService = Retrofit.Builder()
        .baseUrl("baseUrl")
        .addConverterFactory(Mosh)
}