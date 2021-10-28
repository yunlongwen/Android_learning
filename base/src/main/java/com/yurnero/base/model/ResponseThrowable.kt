package com.yurnero.base.model

import java.lang.Exception

class ResponseThrowable(throwable: Throwable?, var code: Int) : Exception(throwable) {
    override var message: String? = null
}