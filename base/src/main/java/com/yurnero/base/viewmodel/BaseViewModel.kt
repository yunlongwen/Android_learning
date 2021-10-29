package com.yurnero.base.viewmodel

import android.util.Log
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.yurnero.base.model.BaseModel
import com.yurnero.base.model.IBaseModelListener
import com.yurnero.base.model.PagingResult
import java.util.*

/**
 * @param RESULT_DATA 页面需要的数据
 */
abstract class BaseViewModel<RESULT_DATA>(savedStateHandle: SavedStateHandle) :
    ViewModel(),
    LifecycleObserver,
    IBaseModelListener<RESULT_DATA> {

    private var model: BaseModel<*, RESULT_DATA>? = null

    public val resultData = MutableLiveData<ArrayList<RESULT_DATA>>()

    public val errorMessage = MutableLiveData<String>()

    public val status = MutableLiveData<ViewStatus>()

    init {
        resultData.value = ArrayList<RESULT_DATA>()
        errorMessage.value = ""
        model = createModel(savedStateHandle)
        status.value = ViewStatus.EMPTY
    }

    open fun fresh() {
        model?.fresh()
    }

    open fun nextPage() {
        model?.loadNextPage()
    }


    override fun onCleared() {
        super.onCleared()
        model?.clear()
    }

    override fun onLoadSuccess(
        model: BaseModel<*, RESULT_DATA>,
        data: ArrayList<RESULT_DATA>,
        vararg pageResult: PagingResult
    ) {
        Log.d("wyl","onLoadSuccess")
        resultData.value?.clear()
        resultData.value?.addAll(data)
        resultData.postValue(resultData.value)
    }

    override fun onLoadFail(
        model: BaseModel<*, RESULT_DATA>,
        prompt: String?,
        vararg pageResult: PagingResult
    ) {
        if (prompt.isNullOrBlank()) {
            errorMessage.postValue("网络错误")
        } else {
            errorMessage.postValue(prompt)
        }
    }

    abstract fun createModel(savedStateHandle: SavedStateHandle): BaseModel<*, RESULT_DATA>
}