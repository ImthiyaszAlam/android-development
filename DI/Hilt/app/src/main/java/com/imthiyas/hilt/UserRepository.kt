package com.imthiyas.hilt

import android.util.Log
import javax.inject.Inject


const val TAG = "HILT"

interface UserRepo {
    fun saveUser(email: String, password: String)
}

class UserRepository @Inject constructor() : UserRepo {
    override fun saveUser(email: String, password: String) {
        Log.d(TAG, "Saved to user DB")
    }
}

class FirebaseRepository : UserRepo {
    override fun saveUser(email: String, password: String) {
        Log.d(TAG, "user saved in firebase")
    }
}

class AWSRepository : UserRepo {
    override fun saveUser(email: String, password: String) {
        Log.d(TAG, "Saved to AWS")
    }

}