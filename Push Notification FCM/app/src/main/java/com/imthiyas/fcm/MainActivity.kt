package com.imthiyas.fcm

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    companion object {
        const val NOTIFICATION_REQ_CODE = 100
    }

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


    private fun requestPostPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            val hasPermission = ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED

            if (hasPermission) {
                return
            } else {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                    NOTIFICATION_REQ_CODE
                )
            }
        }

    }
}