package com.example.dagger_hilt_demo.di

import android.content.Context
import android.content.SharedPreferences
import com.example.dagger_hilt_demo.app.AppController
import com.example.dagger_hilt_demo.utils.Constant.SHARED_PREF_FILE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@InstallIn(ApplicationComponent::class)
@Module
class PrefModule {

    @Provides
    fun provideSharedPreferences(): SharedPreferences =
        AppController.getAppContext().getSharedPreferences(SHARED_PREF_FILE, Context.MODE_PRIVATE)
}