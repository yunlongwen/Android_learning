package com.yurnero.network.base

import android.app.Application

interface INetworkInitInfo {
    fun getAppVersionName(): String

    fun getAppVersionCode(): String

    fun isDebug(): Boolean

    fun applicationContext(): Application
}