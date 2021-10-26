package com.yurnero.mvvm

import LoginParam
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

import com.yurnero.mvvm.api.HmiApiInterface
import com.yurnero.network.HmiNetworkApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        HmiNetworkApi.getService(HmiApiInterface::class.java).login(LoginParam("jiemeng", "123"))
            .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                Log.d("WYL", "login success" + it.result.data)
            }
    }
}