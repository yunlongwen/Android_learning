package com.yurnero.mvvm.api

import LoginParam
import LoginResponse
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST

interface HmiApiInterface {
    @POST("/1.1/functions/login")
    fun login(@Body loginParam: LoginParam): Observable<LoginResponse>
}