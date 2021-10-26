package com.yurnero.network.base

import android.app.Application
import com.yurnero.network.base.interceptor.CommonRequestInterceptor
import com.yurnero.network.base.interceptor.CommonResponseInterceptor
import com.yurnero.network.environment.IEnvironment
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

abstract class NetworkApi protected constructor() : IEnvironment {
    private val retrofitMap: HashMap<String, Retrofit> = hashMapOf()
    private var isDevEnvironment: Boolean = true
    private var okHttpClient: OkHttpClient? = null
    private var baseUrl: String = ""

    init {
        baseUrl = if (isDevEnvironment) {
            getDevHost()
        } else {
            getProdHost()
        }
    }

    companion object {
        private var context: Application? = null
        private var initInfo: INetworkInitInfo? = null
        fun init(networkInitInfo: INetworkInitInfo) {
            initInfo = networkInitInfo
            context = networkInitInfo.applicationContext()
        }
    }


    fun <T> getRetrofit(service: Class<T>): Retrofit {
        if (retrofitMap[baseUrl + service.name] != null) {
            return retrofitMap[baseUrl + service.name]!!
        }

        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(getOkhttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
        retrofitMap[baseUrl + service.name] = retrofit
        return retrofit
    }

    private fun getOkhttpClient(): OkHttpClient {
        if (okHttpClient == null) {
            okHttpClient = OkHttpClient.Builder().apply {
                addInterceptor(CommonRequestInterceptor())
                addInterceptor(CommonResponseInterceptor())
                if (getInterceptor() != null) {
                    addInterceptor(getInterceptor()!!)
                }
                if (initInfo != null && initInfo!!.isDebug()) {
                    addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                }
            }.build()
        }
        return okHttpClient!!
    }

    abstract fun getInterceptor(): Interceptor?


}