package com.example.dagger_hilt_demo.ui.event

class BaseEvent<out T>(private val data: T?) {
    private var hasBeenHandled = false

    /**
     * Returns the content and prevents its use again.
     */
    fun getEventIfNotHandled(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            data
        }
    }
}