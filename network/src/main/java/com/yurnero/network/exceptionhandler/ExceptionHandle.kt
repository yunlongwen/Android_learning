package com.yurnero.network.exceptionhandler

import android.net.ParseException
import com.google.gson.JsonParseException
import com.yurnero.network.exceptionhandler.ExceptionHandle.ResponeThrowable
import com.yurnero.network.exceptionhandler.ExceptionHandle.ERROR
import com.yurnero.network.exceptionhandler.ExceptionHandle
import org.json.JSONException
import org.apache.http.conn.ConnectTimeoutException
import retrofit2.HttpException
import java.lang.Exception
import java.lang.RuntimeException
import java.net.ConnectException
import java.net.SocketTimeoutException
import javax.net.ssl.SSLHandshakeException

object ExceptionHandle {
    private const val UNAUTHORIZED = 401
    private const val FORBIDDEN = 403
    private const val NOT_FOUND = 404
    private const val REQUEST_TIMEOUT = 408
    private const val INTERNAL_SERVER_ERROR = 500
    private const val BAD_GATEWAY = 502
    private const val SERVICE_UNAVAILABLE = 503
    private const val GATEWAY_TIMEOUT = 504
    @JvmStatic
    fun handleException(e: Throwable?): ResponeThrowable {
        val ex: ResponeThrowable
        return if (e is HttpException) {
            ex = ResponeThrowable(e, ERROR.HTTP_ERROR)
            when (e.code()) {
                UNAUTHORIZED, FORBIDDEN, NOT_FOUND, REQUEST_TIMEOUT, GATEWAY_TIMEOUT, INTERNAL_SERVER_ERROR, BAD_GATEWAY, SERVICE_UNAVAILABLE -> ex.message =
                    "网络错误"
                else -> ex.message = "网络错误"
            }
            ex
        } else if (e is ServerException) {
            val resultException = e
            ex = ResponeThrowable(resultException, resultException.code)
            ex.message = resultException.message
            ex
        } else if (e is JsonParseException
            || e is JSONException
            || e is ParseException
        ) {
            ex = ResponeThrowable(e, ERROR.PARSE_ERROR)
            ex.message = "解析错误"
            ex
        } else if (e is ConnectException) {
            ex = ResponeThrowable(e, ERROR.NETWORD_ERROR)
            ex.message = "连接失败"
            ex
        } else if (e is SSLHandshakeException) {
            ex = ResponeThrowable(e, ERROR.SSL_ERROR)
            ex.message = "证书验证失败"
            ex
        } else if (e is ConnectTimeoutException) {
            ex = ResponeThrowable(e, ERROR.TIMEOUT_ERROR)
            ex.message = "连接超时"
            ex
        } else if (e is SocketTimeoutException) {
            ex = ResponeThrowable(e, ERROR.TIMEOUT_ERROR)
            ex.message = "连接超时"
            ex
        } else {
            ex = ResponeThrowable(e, ERROR.UNKNOWN)
            ex.message = "未知错误"
            ex
        }
    }

    /**
     * 约定异常
     */
    object ERROR {
        /**
         * 未知错误
         */
        const val UNKNOWN = 1000

        /**
         * 解析错误
         */
        const val PARSE_ERROR = 1001

        /**
         * 网络错误
         */
        const val NETWORD_ERROR = 1002

        /**
         * 协议出错
         */
        const val HTTP_ERROR = 1003

        /**
         * 证书出错
         */
        const val SSL_ERROR = 1005

        /**
         * 连接超时
         */
        const val TIMEOUT_ERROR = 1006
    }

    class ResponeThrowable(throwable: Throwable?, var code: Int) : Exception(throwable) {
        override var message: String? = null
    }

    class ServerException : RuntimeException() {
        var code = 0
        override var message: String? = null
    }
}