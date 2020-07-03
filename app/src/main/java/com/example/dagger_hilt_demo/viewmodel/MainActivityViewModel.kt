package com.example.dagger_hilt_demo.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dagger_hilt_demo.data.model.ListData
import com.example.dagger_hilt_demo.data.remote.Result
import com.example.dagger_hilt_demo.data.remote.repo.Repository
import com.example.dagger_hilt_demo.ui.event.BaseEvent
import com.example.dagger_hilt_demo.ui.event.ConstantNavEvent
import com.google.gson.Gson
import com.google.gson.JsonParseException
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.launch
import org.json.JSONObject

class MainActivityViewModel @ViewModelInject constructor(
    private val gson: Gson,
    private val repository: Repository
) : ViewModel() {

    private val loginNavEvent = MutableLiveData<BaseEvent<ConstantNavEvent>>()
    val navEvent: LiveData<BaseEvent<ConstantNavEvent>> = loginNavEvent

    fun getList() {
        viewModelScope.launch {
            loginNavEvent.value = BaseEvent(ConstantNavEvent.StartLoading)
            val result = repository.getList()
            try {
                when (result) {
                    is Result.Success -> {
                        val jsonObj = JSONObject(result.body.toString())
                        if (jsonObj.has("error")) {
                            loginNavEvent.value =
                                BaseEvent(ConstantNavEvent.Error(jsonObj.getString("error")))
                        } else {
                            try {
                                val data = gson.fromJson(
                                    jsonObj.toString(),
                                    ListData::class.java
                                )
                                loginNavEvent.value =
                                    BaseEvent(
                                        ConstantNavEvent.OnResponse(
                                            data
                                        )
                                    )
                            } catch (e: JsonParseException) {
                                e.printStackTrace()
                                loginNavEvent.value =
                                    BaseEvent(ConstantNavEvent.Error(e.message))
                            }
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