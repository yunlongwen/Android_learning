package com.yurnero.login.model

import com.yurnero.base.model.BaseDataObserver
import com.yurnero.network.observer.BaseObserver

class LoginObserver(dataObserver: BaseDataObserver<LoginResponse>, model: LoginModel) :
    BaseObserver<LoginResponse>(dataObserver, model) {
    override fun onNext(t: LoginResponse) {
        dataObserver?.onTransformToViewModels(t,false)
    }

    override fun onComplete() {
    }
}