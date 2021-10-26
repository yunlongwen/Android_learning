package com.yurnero.mvvm.application

import android.app.Application
import com.yurnero.network.base.NetworkApi

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        NetworkApi.init(NetworkRequestInfo(this))
    }
}