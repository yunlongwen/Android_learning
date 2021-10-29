package com.yurnero.base.model

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * 给viewmodel留了public接口
 */
abstract class BaseModel<NETWORK_DATA, RESULT_DATA>(
    private var isPaging: Boolean,
    listener: IBaseModelListener<RESULT_DATA>,
    private var cacheKey: String
) : BaseDataObserver<NETWORK_DATA> {
    protected var iBaseModelListener: IBaseModelListener<RESULT_DATA>? = listener
    private var compositeDisposable: CompositeDisposable? = null

    open fun fresh() {
        load()
    }

    open fun loadNextPage() {

    }

    open fun clear() {
        if (compositeDisposable != null && !compositeDisposable?.isDisposed!!) {
            compositeDisposable?.dispose()
        }
    }

    open fun addDisposable(d: Disposable?) {
        if (d == null) {
            return
        }
        if (compositeDisposable == null) {
            compositeDisposable = CompositeDisposable()
        }
        compositeDisposable?.add(d)
    }

    abstract fun load()
}