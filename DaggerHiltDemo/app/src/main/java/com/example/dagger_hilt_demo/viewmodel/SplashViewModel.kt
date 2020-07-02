package com.example.dagger_hilt_demo.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dagger_hilt_demo.di.PrefManager
import com.example.dagger_hilt_demo.ui.event.BaseEvent
import com.example.dagger_hilt_demo.ui.event.ConstantNavEvent
import com.example.dagger_hilt_demo.utils.Constant.IS_USER_LOGGED_IN

class SplashViewModel @ViewModelInject constructor(
    private val prefManager: PrefManager
) : ViewModel() {

    private val splashNavEvent = MutableLiveData<BaseEvent<ConstantNavEvent>>()
    val navEvent: LiveData<BaseEvent<ConstantNavEvent>> = splashNavEvent

    fun isUserLogin() {
        splashNavEvent.value =
            BaseEvent(ConstantNavEvent.OnResponse(prefManager.getBoolean(IS_USER_LOGGED_IN, false)))
    }
}