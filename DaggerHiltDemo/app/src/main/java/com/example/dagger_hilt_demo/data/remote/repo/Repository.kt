package com.example.dagger_hilt_demo.data.remote.repo

import com.example.dagger_hilt_demo.data.remote.Result
import com.example.dagger_hilt_demo.data.remote.calls.RemoteCall
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(
    private val remoteCall: RemoteCall
) {
    suspend fun login(email: String, pass: String): Result =
        remoteCall.login(email, pass)

    suspend fun register(email: String, pass: String): Result =
        remoteCall.register(email, pass)

    suspend fun getList(): Result =
        remoteCall.getList()
}