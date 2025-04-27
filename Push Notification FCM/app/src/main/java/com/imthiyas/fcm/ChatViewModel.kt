package com.imthiyas.fcm

import androidx.lifecycle.ViewModel
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

class ChatViewModel : ViewModel() {

    private val api: FCMApiService = Retrofit.Builder()
        .baseUrl("baseUrl")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .create()
}