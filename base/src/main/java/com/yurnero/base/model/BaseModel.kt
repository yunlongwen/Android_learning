package com.yurnero.base.model

import io.reactivex.disposables.Disposable


abstract class BaseModel<NETWORK_DATA, RESULT_DATA>(
    private var isPaging: Boolean,
    listener: IBaseModelListener<RESULT_DATA>,
    private var cacheKey: String
) : BaseDataObserver<NETWORK_DATA> {
    protected var iBaseModelListener: IBaseModelListener<RESULT_DATA>? = listener

    fun fresh() {
        load()
    }

    fun loadNextPage() {

    }

    fun clear() {

    }

    fun addDisposable(d: Disposable) {

    }

    abstract fun load()
}