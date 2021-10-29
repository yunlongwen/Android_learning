package com.yurnero.network

import com.yurnero.network.base.NetworkApi
import okhttp3.Interceptor
import okhttp3.Request
import retrofit2.create

class HmiNetworkApi : NetworkApi() {

    companion object {
        private var hmiNetworkApi: HmiNetworkApi? = null
        fun getInstance(): HmiNetworkApi {
            if (hmiNetworkApi == null) {
                synchronized(HmiNetworkApi::class.java) {
                    if (hmiNetworkApi == null) {
                        hmiNetworkApi = HmiNetworkApi()
                    }
                }
            }
            return hmiNetworkApi!!
        }

        inline fun <reified T> getService(service: Class<T>): T {
            return getInstance().getRetrofit(service).create()
        }
    }


    override fun getInterceptor(): Interceptor? {

        return Interceptor {
            val original: Request = it.request()
            val builder: Request.Builder = original.newBuilder()
                .header("X-LC-ID", "yynqiWax1jvQBhUgv571UASb-gzGzoHsz")
                .header("X-LC-Key", "kyctX1GO7U8YQ86xBxnelEDl")
                .header("X-LC-Session", "arp6su86pzqnztgsdp0ytezjb")

            it.proceed(builder.build())
        }
    }

    override fun getProdHost(): String {
        return "https://hmi.twiot.top"
    }

    override fun getDevHost(): String {
        return "https://hmi-dev.twiot.top"
    }
}