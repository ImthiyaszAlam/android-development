package com.imthiyas.service.worker

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class DemoWorker(context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {


    private val WORKER_TAG = "workerDebug"
    @SuppressLint("RestrictedApi")
    override fun doWork(): Result {
        performWork()
        return Result.retry()
    }

    private fun performWork() {
        Thread.sleep(2000)
        Log.d(WORKER_TAG, "task completed")
    }

}