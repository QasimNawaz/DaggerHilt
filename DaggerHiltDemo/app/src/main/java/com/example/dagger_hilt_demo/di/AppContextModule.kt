package com.example.dagger_hilt_demo.di

import android.content.Context
import com.example.dagger_hilt_demo.app.AppController
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@InstallIn(ApplicationComponent::class)
@Module
class AppContextModule {
    @Provides
    fun getApplicationContext(): Context = AppController.getAppContext().applicationContext
}