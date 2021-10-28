package com.yurnero.base.model

/**
 * 网络（原始）数据给viewmodel层的监听
 */
interface BaseDataObserver<NETWORK_DATA> {
    fun onTransformToViewModels(data: NETWORK_DATA, isCache: Boolean)

    fun onFailure(e: ResponseThrowable)

}
