package com.imthiyas.kotlinflows

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.internal.NopCollector.emit
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        GlobalScope.launch {
            
        }

        fun producer():Flow<Int>{
            val list  = listOf(1,2,3,4,5,6,7,8,9,10)
            list.forEach {
                delay(1000),
                emit(it)
            }
        }
    }
}