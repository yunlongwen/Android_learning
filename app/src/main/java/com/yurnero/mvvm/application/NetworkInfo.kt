package com.yurnero.mvvm.application

import android.app.Application
import com.yurnero.mvvm.BuildConfig
import com.yurnero.network.base.INetworkInitInfo

class NetworkRequestInfo(private val applicationContext: Application) : INetworkInitInfo {

    override fun getAppVersionName(): String {
        return BuildConfig.VERSION_NAME
    }

    override fun getAppVersionCode(): String {
        return BuildConfig.VERSION_CODE.toString()
    }

    override fun isDebug(): Boolean {
        return BuildConfig.DEBUG
    }

    override fun applicationContext(): Application {
        return applicationContext
    }

}