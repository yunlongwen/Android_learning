package com.yurnero.login.model

import android.util.Log
import com.yurnero.base.model.BaseDataObserver
import com.yurnero.base.model.BaseModel
import com.yurnero.base.model.IBaseModelListener
import com.yurnero.base.model.ResponseThrowable
import com.yurnero.login.api.LoginApiInterface
import com.yurnero.network.HmiNetworkApi
import io.reactivex.Observer

class LoginModel(listener: IBaseModelListener<LoginData>) :
    BaseModel<LoginResponse, LoginData>(
        listener = listener,
        isPaging = false,
        cacheKey = "key_login_data"
    ) {
    override fun onTransformToViewModels(data: LoginResponse, isCache: Boolean) {
        Log.d("wyl", "onTransformToViewModels")
        iBaseModelListener?.onLoadSuccess(model = this, data = arrayListOf(data.result.data))
    }

    override fun onFailure(e: ResponseThrowable) {
        Log.d("wyl", "onFailure")
        iBaseModelListener?.onLoadFail(model = this, prompt = e.message)
    }

    override fun load() {
        HmiNetworkApi.getService(LoginApiInterface::class.java)
            .login(LoginParam("jiemeng", "1234"))
            .subscribe(getObserver())
    }

    private fun getObserver(): Observer<LoginResponse> {
        return LoginObserver(
            this as BaseDataObserver<LoginResponse>,
            this
        )
    }
}