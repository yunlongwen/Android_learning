package com.yurnero.network.beans

import com.google.gson.annotations.SerializedName

open class BaseResponse<T>(
    @SerializedName("result") val result: T
)