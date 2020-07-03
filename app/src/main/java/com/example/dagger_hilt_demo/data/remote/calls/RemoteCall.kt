package com.example.dagger_hilt_demo.data.remote.calls

import com.example.dagger_hilt_demo.data.remote.Result
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteCall
@Inject constructor(
    private val networkCall: NetworkCall
) {

    suspend fun login(email: String, pass: String): Result =
        networkCall.post<String>(
            "/api/login",
            mutableMapOf("email" to email, "password" to pass)
        )

    suspend fun register(email: String, pass: String): Result =
        networkCall.post<String>(
            "/api/register",
            mutableMapOf("email" to email, "password" to pass)
        )

    suspend fun getList(): Result =
        networkCall.get<String>(
            "/api/unknown",
            null
        )
}