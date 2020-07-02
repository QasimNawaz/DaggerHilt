package com.example.dagger_hilt_demo.data.remote.calls

import android.util.Log
import com.example.dagger_hilt_demo.data.WebService
import com.example.dagger_hilt_demo.utils.exception.ConnectivityException
import okhttp3.ResponseBody
import com.example.dagger_hilt_demo.data.remote.Result
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkCall @Inject constructor(
    val webService: WebService,
    val connectivity: Connectivity
) {
    inline fun <reified T : Any> generalRequest(
        request: () -> Response<ResponseBody>
    ): Result =
        try {
            val response = request()
            Log.d("GENERAL_REQ", "Headers---> ${response.headers()}")
            Log.d("GENERAL_REQ", "RawResponse ---> ${response.raw()}")
            if (response.isSuccessful) {
                val responseString = response.body()?.string()!!
                Log.d("GENERAL_REQ", "response $responseString")
//               declaring another try/catch block to differentiate
//               connection exception from parsing exceptions
                Result.Success(
//                        gson.fromJson(responseString, T::class.java)
                    responseString
                )
            } else {
//              to handle response codes other than 200
                Result.Error(response)
            }
        } catch (exception: Exception) {
//              this catch is like onFailure when we used callbacks
            Log.d("GENERAL_REQ", "Exception ${exception.message!!}")
//               this is custom exception to show network error
            Result.Exception(exception)
        }


    suspend inline fun <reified T : Any> get(
        endpoint: String, //end point
        queryMap: Map<String, String>? //query params
    ): Result {
        Log.d("GENERAL_REQ", "endpoint : $endpoint")
        Log.d("GENERAL_REQ", "queryMap: $queryMap")
        //        if no internet connection available throw exception and return
        if (!connectivity.isNetworkConnected()) {
            return Result.Exception(ConnectivityException())
        }
        return if (queryMap == null)
            generalRequest<T> { webService.get(endpoint) }
        else
            generalRequest<T> { webService.get(endpoint, queryMap) }
    }


    suspend inline fun <reified T : Any> post(
        endpoint: String, //end point
        queryMap: Map<String, String>? //query params
    ): Result {
        Log.d("GENERAL_REQ", "endpoint : $endpoint")
        Log.d("GENERAL_REQ", "queryMap: $queryMap")
        //        if no internet connection available throw exception and return
        if (!connectivity.isNetworkConnected()) {
            return Result.Exception(ConnectivityException())
        }
        return if (queryMap == null)
            generalRequest<T> { webService.post(endpoint) }
        else
            generalRequest<T> { webService.post(endpoint, queryMap) }
    }
}