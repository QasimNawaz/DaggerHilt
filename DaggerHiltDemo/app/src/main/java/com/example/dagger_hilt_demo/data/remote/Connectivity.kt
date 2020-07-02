//package com.example.dagger_hilt_demo.data.remote
//
//import android.content.Context
//import android.net.ConnectivityManager
//import android.net.NetworkCapabilities
//import android.os.Build
//import dagger.Module
//import dagger.Provides
//import dagger.hilt.InstallIn
//import dagger.hilt.android.components.ApplicationComponent
//import dagger.hilt.android.qualifiers.ApplicationContext
//import javax.inject.Inject
//import javax.inject.Singleton
//
//@InstallIn(ApplicationComponent::class)
//@Module
//class Connectivity {
//
//    @Singleton
//    @Provides
//    @Suppress("DEPRECATION")
//    fun isNetworkConnected(@ApplicationContext context: Context): Boolean {
//        var result = false
//        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            cm?.run {
//                cm.getNetworkCapabilities(cm.activeNetwork)?.run {
//                    result = when {
//                        hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
//                        hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
//                        hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
//                        else -> false
//                    }
//                }
//            }
//        } else {
//            cm?.run {
//                cm.activeNetworkInfo?.run {
//                    if (type == ConnectivityManager.TYPE_WIFI) {
//                        result = true
//                    } else if (type == ConnectivityManager.TYPE_MOBILE) {
//                        result = true
//                    }
//                }
//            }
//        }
//        return result
//    }
//}