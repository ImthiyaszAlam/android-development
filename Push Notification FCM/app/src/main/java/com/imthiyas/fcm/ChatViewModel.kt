package com.imthiyas.fcm

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
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

    fun onSubmitRemoteToken() {
        state = state.copy(
            isEnteringToken = false
        )
    }

    fun onMessageChange(message: String) {
        state = state.copy(
            messageText = message
        )
    }

    fun sendMessage(isBroadCast: Boolean) {
        viewModelScope.launch {
            val sendMessageDto = SendMessageDto(
                to = if (isBroadCast) null else state.remoteTokenText,
                notification = NotificationBody(
                    title = "Alam",
                    body = state.messageText
                )
            )

            try {
                if (isBroadCast) {
                    api.broadCast(sendMessageDto)
                } else {
                    api.sendMessage(sendMessageDto)
                }
                state = state.copy(
                    messageText = ""
                )
            } catch (e: Exception) {
                val message = e.message ?: "Unknown Error"
                Log.d("Error", "$message")
            }
        }
    }
}