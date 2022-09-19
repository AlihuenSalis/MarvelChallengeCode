package com.example.challengecode

import android.app.Application
import com.example.challengecode.domain.model.user.User
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class Application : Application() {

    companion object {
        val PREFS = "challenge.conf"
        val PREFSREMEMBER = "challenge.remember"
    }

    fun loadCredentials(): User {
        val prefs = getSharedPreferences(PREFS, MODE_PRIVATE)
        val usernameShared = prefs.getString("username", null)
        val passwordShared = prefs.getString("password", null)
        return User(usernameShared, passwordShared)
    }
}