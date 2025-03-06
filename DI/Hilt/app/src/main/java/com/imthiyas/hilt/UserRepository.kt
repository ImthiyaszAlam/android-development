package com.imthiyas.hilt

import android.util.Log

class UserRepository {
    fun saveUser(email: String, password: String) {
        Log.d("USER", "saved to db")
    }
}