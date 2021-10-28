package com.yurnero.network.exceptionhandler

import android.net.ParseException
import com.google.gson.JsonParseException
import com.yurnero.base.model.ResponseThrowable
import org.json.JSONException
import retrofit2.HttpException
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
    private const val PARAMETERS_ERROR = 400

    fun handleException(e: Throwable?): ResponseThrowable {
        val ex: ResponseThrowable
        return if (e is HttpException) {

            when (e.code()) {
                UNAUTHORIZED, FORBIDDEN, NOT_FOUND, REQUEST_TIMEOUT, GATEWAY_TIMEOUT, INTERNAL_SERVER_ERROR, BAD_GATEWAY, SERVICE_UNAVAILABLE
                -> {
                    ex = ResponseThrowable(e, ERROR.HTTP_ERROR)
                    ex.message = "网络错误"
                }
                PARAMETERS_ERROR -> {
                    ex = ResponseThrowable(e, ERROR.PARAMETERS_ERROR)
                    ex.message = "参数错误"
                }
                else -> {
                    ex = ResponseThrowable(e, ERROR.HTTP_ERROR)
                    ex.message = "网络错误"
                }
            }
            ex
        } else if (e is ServerException) {
            ex = ResponseThrowable(e, e.code)
            ex.message = e.message
            ex
        } else if (e is JsonParseException
            || e is JSONException
            || e is ParseException
        ) {
            ex = ResponseThrowable(e, ERROR.PARSE_ERROR)
            ex.message = "解析错误"
            ex
        } else if (e is ConnectException) {
            ex = ResponseThrowable(e, ERROR.NETWORK_ERROR)
            ex.message = "连接失败"
            ex
        } else if (e is SSLHandshakeException) {
            ex = ResponseThrowable(e, ERROR.SSL_ERROR)
            ex.message = "证书验证失败"
            ex
        } else if (e is SocketTimeoutException) {
            ex = ResponseThrowable(e, ERROR.TIMEOUT_ERROR)
            ex.message = "连接超时"
            ex
        } else {
            ex = ResponseThrowable(e, ERROR.UNKNOWN)
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
        const val NETWORK_ERROR = 1002

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

        /**
         * 参数错误
         */
        const val PARAMETERS_ERROR = 1007
    }

    class ServerException : RuntimeException() {
        var code = 0
        override var message: String? = null
    }
}