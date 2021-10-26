package com.yurnero.network.interceptor


import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class CommonRequestInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val original: Request = chain.request()
        val builder: Request.Builder = original.newBuilder()
            .header("Content-Type", "application/json")
            .header("Accept", "application/json")
        return chain.proceed(builder.build())
    }
}