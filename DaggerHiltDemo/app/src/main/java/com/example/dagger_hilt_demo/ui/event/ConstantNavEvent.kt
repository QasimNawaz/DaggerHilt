package com.example.dagger_hilt_demo.ui.event

sealed class ConstantNavEvent {
    object StartLoading : ConstantNavEvent()
    object StopLoading : ConstantNavEvent()
    class OnResponse(val data: Any?) : ConstantNavEvent()
    class Exception(val exception: java.lang.Exception?) : ConstantNavEvent()
    class Error(val error: String?) : ConstantNavEvent()
}