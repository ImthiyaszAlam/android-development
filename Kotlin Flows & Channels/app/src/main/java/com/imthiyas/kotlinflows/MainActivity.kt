package com.imthiyas.kotlinflows

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    companion object {
        const val TAG = "MainActivityDebug"
    }


    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        GlobalScope.launch(Dispatchers.Main) {

            producer()
                .onStart {
                    Log.d(TAG, "Starting out")
                }
                .onCompletion {
                    Log.d(TAG, "Completed")
                }
                .onEach {
                    Log.d(TAG, "About to emi $it")
                }
                .collect {
                    Log.d(TAG, it.toString())
                }
        }

    }


    suspend fun producer(): Flow<Int> = flow<Int> {
        val list = listOf(1, 2, 3, 4, 5, 6)
        list.forEach {
            delay(1000)
            emit(it)
        }
    }
}