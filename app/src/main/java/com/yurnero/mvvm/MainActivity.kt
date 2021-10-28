package com.yurnero.mvvm

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.yurnero.base.autoservice.BaseServiceLoader
import com.yurnero.common.autoservice.ILoginService
import com.yurnero.mvvm.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        val loginService = BaseServiceLoader.load(ILoginService::class.java)
        val loginFragment = loginService?.getLoginFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, loginFragment!!)
        transaction.commit()
    }
}
