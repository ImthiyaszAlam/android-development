package com.imthiyas.service.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Handler
import android.os.HandlerThread
import android.os.IBinder
import android.os.Looper
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.imthiyas.service.MainActivity


class OfferService : Service() {

    lateinit var handlerThread: HandlerThread
    lateinit var handler: Handler


    override fun onCreate() {
        super.onCreate()
        handlerThread = HandlerThread("OfferService")
        handlerThread.start()
        handler = Handler(handlerThread.looper)
    }


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        handler.post {
            trackSeconds()
        }
        return super.onStartCommand(intent, flags, startId)
    }


    fun getPendingIntent(): PendingIntent {
        val intent = Intent(this, MainActivity::class.java)
        return PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun createNotificationChannel(context: Context): NotificationChannel {
        val channel = NotificationChannel(
            "ChannelId",
            "Imthiyas",
            NotificationManager.IMPORTANCE_DEFAULT
        )
        val notificationManager =
            ContextCompat.getSystemService(context, NotificationManager::class.java)

        notificationManager?.createNotificationChannel(channel)

        return channel
    }


    fun getNotificationBuilder(): NotificationCompat.Builder {
        val notificationBuilder = NotificationCompat.Builder(this, "ChannelId")
            .setContentTitle("Offer you can't refuse")
            .setContentText("60% Off")
            .setContentIntent(getPendingIntent())
            .setOngoing(true)
        return notificationBuilder
    }

    private fun trackSeconds() {
        for (i in 10 downTo 1) {
            Thread.sleep(1000)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onBind(intent: Intent?): IBinder? {
        TODO("Not yet implemented")
    }
}