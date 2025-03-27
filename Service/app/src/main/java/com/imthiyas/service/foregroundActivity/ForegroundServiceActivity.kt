package com.imthiyas.service.foregroundActivity

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.imthiyas.service.R
import com.imthiyas.service.backgroundService.LoggerService
import com.imthiyas.service.databinding.ActivityForegroundServiceBinding

class ForegroundServiceActivity : AppCompatActivity() {


    private lateinit var binding: ActivityForegroundServiceBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForegroundServiceBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.startServiceBtn.setOnClickListener {
            startForegroundService(Intent(this, LoggerService::class.java))
        }

        binding.stopServiceBtn.setOnClickListener {
            stopService(Intent(this, LoggerService::class.java))
        }

    }
}