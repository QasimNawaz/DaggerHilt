package com.example.dagger_hilt_demo.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class AppController : Application(){

    override fun onCreate() {
        super.onCreate()
        ctx = this
    }

    companion object {
        val TAG: String = AppController::class.java
            .simpleName
        lateinit var ctx: AppController
        fun getAppContext(): AppController {
            return ctx
        }
    }
}