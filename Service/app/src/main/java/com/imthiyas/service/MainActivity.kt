package com.imthiyas.service

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.work.BackoffPolicy
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequest
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.imthiyas.service.worker.DemoWorker
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private val workManager = WorkManager.getInstance(this)

    private val Activity_TAG = "ActivityDebug"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        doWorkerWork()

    }

    private fun doWorkerWork() {
        val request = OneTimeWorkRequest.Builder(DemoWorker::class.java)
            .setConstraints(
                (Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED)).build()
            )
            .setBackoffCriteria(
                BackoffPolicy.LINEAR,
                10,
                TimeUnit.SECONDS
            )
            .build()
        workManager.enqueue(request)
        workManager.beginWith(request).then(request).then(request).enqueue()


        workManager.getWorkInfoByIdLiveData(request.id).observe(this) {
            if (it != null) {
                printStatus(it.state.name)
            }
        }
    }

    private fun printStatus(name: String) {
        Log.d(Activity_TAG, name)
    }
}