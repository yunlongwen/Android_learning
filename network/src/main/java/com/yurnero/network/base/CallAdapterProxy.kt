package com.yurnero.network.base

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.CallAdapter
import java.lang.reflect.Type

internal class CallAdapterProxy<R>(
    private val adapter: CallAdapter<R, Any>?,
) : CallAdapter<R, Observable<*>> {

    override fun responseType(): Type? {
        return adapter?.responseType()
    }

    override fun adapt(call: Call<R>): Observable<*>? {
        if (null != adapter) {
            var result: Any? = adapter.adapt(call)
            if (null != result && result is Observable<*>) {
                var observable: Observable<*> = result
                observable.apply {
                    subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                        .let {
                            observable = it
                        }
                }
                return observable
            }
        }
        return null
    }
}