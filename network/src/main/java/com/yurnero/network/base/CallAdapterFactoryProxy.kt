package com.yurnero.network.base

import retrofit2.CallAdapter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.lang.reflect.Type

class CallAdapterFactoryProxy private constructor(
    private val factory: CallAdapter.Factory?
) : CallAdapter.Factory() {

    companion object {
        fun create(
            factory: CallAdapter.Factory? = RxJava2CallAdapterFactory.create()
        ) = CallAdapterFactoryProxy(factory = factory)
    }

    //TODO
    fun addInterceptor(): CallAdapterFactoryProxy {
        return this@CallAdapterFactoryProxy
    }

    override fun get(
        returnType: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {
        if (null != factory) {
            val adapter = factory.get(returnType, annotations, retrofit)
            if (null != adapter) {
                return CallAdapterProxy(adapter as CallAdapter<Any, Any>?)
            }
        }
        return null
    }
}