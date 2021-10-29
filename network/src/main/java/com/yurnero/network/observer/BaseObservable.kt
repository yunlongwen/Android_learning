package com.yurnero.network.observer

import com.yurnero.base.model.BaseDataObserver
import com.yurnero.base.model.BaseModel
import com.yurnero.base.model.ResponseThrowable
import com.yurnero.network.exceptionhandler.ExceptionHandle
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

abstract class BaseObserver<T>(dataObserver: BaseDataObserver<T>, model: BaseModel<*, *>) :
    Observer<T> {
    protected var dataObserver: BaseDataObserver<T>? = dataObserver
    protected var model: BaseModel<*, *>? = model

    override fun onSubscribe(d: Disposable) {
        model?.addDisposable(d)
    }


    override fun onError(e: Throwable) {
        if (e is ResponseThrowable) {
            dataObserver?.onFailure(e)
        } else {
            dataObserver?.onFailure(
                ResponseThrowable(
                    e, ExceptionHandle
                        .ERROR.UNKNOWN
                )
            )
        }

    }
}