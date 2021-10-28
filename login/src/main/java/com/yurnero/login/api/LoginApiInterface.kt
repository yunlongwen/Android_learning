package com.yurnero.login.api

import com.yurnero.login.model.LoginParam
import com.yurnero.login.model.LoginResponse
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApiInterface {
    @POST("/1.1/functions/login")
    fun login(@Body loginParam: LoginParam): Observable<LoginResponse>
}