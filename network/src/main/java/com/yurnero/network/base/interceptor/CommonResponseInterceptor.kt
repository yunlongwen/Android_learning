package com.yurnero.network.base.interceptor


import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

class CommonResponseInterceptor : Interceptor {
    companion object {
        private const val TAG = "CommonResponseInterceptor"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())
        Log.d(TAG, "response")
        return response
    }
}