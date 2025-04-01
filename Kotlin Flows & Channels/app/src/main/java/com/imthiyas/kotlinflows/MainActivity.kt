package com.imthiyas.kotlinflows

import android.os.Bundle
import android.util.FloatProperty
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import kotlin.system.measureTimeMillis

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

            val time = measureTimeMillis {
                producer()
                    .map {
                        delay(1000)
                        it * 2
                        Log.d(TAG, Thread.currentThread().name)
                    }
                    .filter {
                        delay(500)
                        Log.d(TAG, Thread.currentThread().name)
                        it < 8

                    }
                    .flowOn(Dispatchers.IO)
                    .buffer(3)
                    .collect {
                        delay(1500)
                        Log.d(TAG, it.toString())
                        Log.d(TAG, Thread.currentThread().name)
                    }
            }

            Log.d(TAG, "$time")

            /*        .onStart {
                        Log.d(TAG, "Starting out")
                    }
                    .onCompletion {
                        Log.d(TAG, "Completed")
                    }
                    .onEach {
                        Log.d(TAG, "About to emi $it")
                    }*/


        }

        lifecycleScope.launch {
            getNotes()
                .map {
                    FormattedNote(it.isActive, it.title.uppercase(), it.description)
                }
                .filter {
                    it.isActive
                }
                .collect {
                    delay(1500)
                    Log.d(TAG, it.toString())
                }
        }


    }


    suspend fun producer(): Flow<Int> = flow<Int> {
        val list = listOf(1, 2, 3, 4, 5, 6)
        list.forEach {
            delay(1000)
            emit(it)
            throw Exception("Eaception")
        }
    }
        .catch {
            Log.d(TAG, "${it.message}")
            emit(-1)
        }

    private fun getNotes(): Flow<Note> {
        val list = listOf(
            Note(1, true, "Note1", "Description1"),
            Note(1, true, "Note1", "Description1"),
            Note(1, true, "Note1", "Description1"),
            Note(1, true, "Note1", "Description1")
        )
        return list.asFlow()
    }


    data class Note(
        val id: Int,
        val isActive: Boolean,
        val title: String,
        val description: String
    )

    data class FormattedNote(

        val isActive: Boolean,
        val title: String,
        val description: String
    )
}