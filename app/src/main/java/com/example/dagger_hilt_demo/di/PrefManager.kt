package com.example.dagger_hilt_demo.di

import android.content.SharedPreferences
import javax.inject.Inject

class PrefManager @Inject constructor(
    private val preferences: SharedPreferences
) {
    fun getBoolean(key: String, defaultValue: Boolean): Boolean {
        return preferences.getBoolean(key, defaultValue)
    }

    fun putBoolean(key: String, value: Boolean) {
        try {
            println("$key $value")
            preferences.edit().putBoolean(key, value).apply()
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

    fun getString(key: String, defaultValue: String): String {
        return preferences.getString(key, defaultValue)!!
    }

    fun putString(key: String, value: String) {
        try {
            println("$key $value")
            preferences.edit().putString(key, value).apply()
        } catch (ex: Exception) {
            ex.printStackTrace()
        }

    }

    fun clearAllPreferences() {
        preferences.edit().clear().apply()
    }
}