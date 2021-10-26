package com.yurnero.network.environment

interface IEnvironment {
    /**
     * 生产环境Url
     */
    fun getProdHost(): String

    /**
     * 开发环境Url
     */
    fun getDevHost(): String
}