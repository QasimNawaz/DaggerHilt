package com.example.dagger_hilt_demo.data

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*

interface WebService {

    @GET
    suspend fun get(
        @Url endpoint: String,
        @QueryMap query: @JvmSuppressWildcards Map<String, String>
    ): Response<ResponseBody>

    @GET
    suspend fun get(
        @Url endpoint: String
    ): Response<ResponseBody>

    @FormUrlEncoded
    @POST
    suspend fun post(
        @Url endpoint: String,
        @FieldMap query: @JvmSuppressWildcards Map<String, String>
    ): Response<ResponseBody>

    @FormUrlEncoded
    @POST
    suspend fun post(
        @Url endpoint: String
    ): Response<ResponseBody>
}