package com.imthiyas.fcm

data class ChatState(
    val isEnteringToken: Boolean = true,
    val remoteTokenText: String = "",
    val messageText: String = ""
)
