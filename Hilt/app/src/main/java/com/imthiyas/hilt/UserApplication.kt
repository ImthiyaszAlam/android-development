package com.imthiyas.hilt

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class UserApplication : Application() {


    @Inject
    private lateinit var userRepository: UserRepository
    override fun onCreate() {
        super.onCreate()
        userRepository.saveUser("deve@gmail.com", "12345")
    }
}