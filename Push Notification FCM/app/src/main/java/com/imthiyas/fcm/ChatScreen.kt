package com.imthiyas.fcm

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.ktx.messaging
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

@Composable
fun ChatScreen(
    messageText: String,
    onMessageChange: (String) -> Unit,
    onMessageSend: () -> Unit,
    onMessageBroadCast: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        OutlinedTextField(
            value = messageText,
            onValueChange = onMessageChange,
            placeholder = {
                Text("Enter a Message")
            },
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .fillMaxWidth()
        )


        Spacer(
            Modifier.height(16.dp)
        )

        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            OutlinedButton(
                onClick = {
                    scope.launch {
                        val token = Firebase.messaging.token.await()
                        clipboardManager.setText(AnnotatedString(token))
                        Toast.makeText(context, "Copied Local Token", Toast.LENGTH_LONG).show()
                    }
                }
            ) {
                Text("Copy Token")
            }
        }

    }


}