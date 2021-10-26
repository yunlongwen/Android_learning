package com.yurnero.network.base

import io.reactivex.functions.Function
import retrofit2.Call
import retrofit2.CallAdapter
import java.lang.Exception
import java.lang.reflect.Type

class LocalCallAdapter<R, W>(mAdapter: CallAdapter<R, W>, mFunction: Function<W, W>) :
    CallAdapter<R, W> {
    private var adapter: CallAdapter<R, W> = mAdapter

    private var function: Function<W, W> = mFunction

    companion object {
        fun <R, W> create(
            mAdapter: CallAdapter<R, W>,
            mFunction: Function<W, W>
        ): CallAdapter<R, W> {
            return LocalCallAdapter(mAdapter, mFunction)
        }
    }

    override fun responseType(): Type {
        return adapter.responseType()
    }

    override fun adapt(call: Call<R>): W {
        val adapt: W = adapter.adapt(call)
        try {
            return function.apply(adapt)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return adapt
    }
}