package com.yurnero.base.model

interface IBaseModelListener<RESULT_DATA> {
    fun onLoadSuccess(
        model: BaseModel<*, RESULT_DATA>,
        data: ArrayList<RESULT_DATA>,
        vararg pageResult: PagingResult
    )

    fun onLoadFail(
        model: BaseModel<*, RESULT_DATA>,
        prompt: String?,
        vararg pageResult: PagingResult
    )
}