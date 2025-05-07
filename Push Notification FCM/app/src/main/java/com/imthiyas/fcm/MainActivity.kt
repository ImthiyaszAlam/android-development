package com.imthiyas.fcm

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

class MainActivity : AppCompatActivity() {


    private val viewModel: ChatViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Surface(
                color = MaterialTheme.colorScheme.background,
                modifier = Modifier.fillMaxSize()
            ) {
                val state = viewModel.state
                if (state.isEnteringToken) {
                    EnterTokenDialog(
                        token = state.remoteTokenText,
                        onTokenChange = viewModel::onRemoteTokenChange,
                        onSubmit = viewModel::onSubmitRemoteToken
                    )
                } else {
                    ChatScreen(
                        messageText = state.messageText,
                        onMessageSend = {
                            viewModel.sendMessage(isBroadCast = false)
                        },
                        onMessageBroadCast = {
                            viewModel.sendMessage(isBroadCast = true)
                        },
                        onMessageChange = viewModel::onMessageChange

                    )
                }
            }

        }
    }
}