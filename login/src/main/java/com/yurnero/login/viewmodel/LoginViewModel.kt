package com.yurnero.login.viewmodel

import androidx.lifecycle.SavedStateHandle
import com.yurnero.base.model.BaseModel
import com.yurnero.base.model.IBaseModelListener
import com.yurnero.base.viewmodel.BaseViewModel
import com.yurnero.login.model.LoginData
import com.yurnero.login.model.LoginModel

class LoginViewModel(savedStateHandle: SavedStateHandle) :
    BaseViewModel<LoginData>(savedStateHandle) {
    override fun createModel(savedStateHandle: SavedStateHandle): BaseModel<*, LoginData> {
        return LoginModel(this as IBaseModelListener<LoginData>)
    }
}