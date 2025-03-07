package com.imthiyas.hilt

import android.util.Log
import javax.inject.Inject


const val TAG = "HILT"

class UserRepository @Inject constructor(val loggerService: LoggerService) {
    fun saveUser(email: String, password: String) {
        Log.d(TAG, "saved to db")
        val message = "Logging"
        loggerService.log(message)
    }
}