package com.imthiyas.service.backgroundService

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import kotlin.concurrent.thread

class LoggerService : Service() {

    companion object {
        const val BACKGROUND_SERVICE = "LoggerService"
    }

    override fun onCreate() {
        super.onCreate()
        Log.d(BACKGROUND_SERVICE, "onCreate called")

    }


    override fun onDestroy() {
        super.onDestroy()
        Log.d(BACKGROUND_SERVICE, "onDestroy called")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(BACKGROUND_SERVICE, "onStartCommand called")
        thread(start = true) {
            while (true) {
                Log.d(BACKGROUND_SERVICE, "Logging Messages")
                Thread.sleep(1000)
            }
        }
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent?): IBinder? {
        Log.d(BACKGROUND_SERVICE, "onBind called")
        return null
    }
}