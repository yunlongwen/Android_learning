package com.yurnero.login

import android.util.Log
import androidx.fragment.app.Fragment
import com.google.auto.service.AutoService
import com.yurnero.common.autoservice.ILoginService
import com.yurnero.login.view.LoginFragment

@AutoService(ILoginService::class)
open class LoginService : ILoginService {
    override fun getLoginFragment(): Fragment {
        return LoginFragment()
    }
}