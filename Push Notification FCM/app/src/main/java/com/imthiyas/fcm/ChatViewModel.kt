package com.imthiyas.fcm

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

class ChatViewModel : ViewModel() {


    var state by mutableStateOf(ChatState())
        private set

    private val api: FCMApiService = Retrofit.Builder()
        .baseUrl("baseUrl")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .create()

    fun onRemoteTokenChange(token: String) {
        state = state.copy(
            remoteTokenText = token
        )
    }

    fun onSubmitRemoteToken(){
        state = state.copy(
            isEnteringToken = false
        )
    }
}