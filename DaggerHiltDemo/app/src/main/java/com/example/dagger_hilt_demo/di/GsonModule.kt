package com.example.dagger_hilt_demo.di

import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@InstallIn(ApplicationComponent::class)
@Module
class GsonModule {

    @Provides
    fun provideGson() = Gson()
}