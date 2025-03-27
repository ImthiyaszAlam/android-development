package com.imthiyas.service.backgroundService

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.imthiyas.service.R
import com.imthiyas.service.databinding.ActivityBackgroundServiceBinding

class BackgroundServiceActivity : AppCompatActivity() {


    private lateinit var binding: ActivityBackgroundServiceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBackgroundServiceBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.startServiceBtn.setOnClickListener {
            startService(Intent(this, LoggerService::class.java))
        }

        binding.stopServiceBtn.setOnClickListener {
            stopService(Intent(this, LoggerService::class.java))
        }

    }
}