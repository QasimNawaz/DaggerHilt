package com.example.dagger_hilt_demo.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dagger_hilt_demo.data.remote.Result
import com.example.dagger_hilt_demo.data.remote.repo.Repository
import com.example.dagger_hilt_demo.di.PrefManager
import com.example.dagger_hilt_demo.ui.event.BaseEvent
import com.example.dagger_hilt_demo.ui.event.ConstantNavEvent
import com.example.dagger_hilt_demo.utils.Constant
import com.google.gson.Gson
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.lang.Exception

class SignUpViewModel @ViewModelInject constructor(
    private val gson: Gson,
    private val prefManager: PrefManager,
    private val repository: Repository
) : ViewModel() {

    private val loginNavEvent = MutableLiveData<BaseEvent<ConstantNavEvent>>()
    val navEvent: LiveData<BaseEvent<ConstantNavEvent>> = loginNavEvent

    fun register(email: String, pass: String) {
        viewModelScope.launch {
            val result = repository.register(email, pass)
            try {
                when (result) {
                    is Result.Success -> {
                        val jsonObj = JSONObject(result.body.toString())
                        if (jsonObj.has("token")) {
                            prefManager.putBoolean(Constant.IS_USER_LOGGED_IN, true)
                            prefManager.putString(
                                Constant.TOKEN_PREF,
                                jsonObj.getString("token")
                            )
                            loginNavEvent.value =
                                BaseEvent(ConstantNavEvent.OnResponse(jsonObj))
                        } else if (jsonObj.has("error")) {
                            loginNavEvent.value =
                                BaseEvent(ConstantNavEvent.Error(jsonObj.getString("error")))
                        }

                    }
                    is Result.Error -> {
                        loginNavEvent.value =
                            BaseEvent(ConstantNavEvent.Error(result.errorBody.message()))
                    }
                    is Result.Exception -> {
                        loginNavEvent.value =
                            BaseEvent(ConstantNavEvent.Exception(result.exception))
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                loginNavEvent.value =
                    BaseEvent(ConstantNavEvent.Error(e.message))
            }
        }
    }
}