package com.example.fanfun.network

import com.google.gson.annotations.SerializedName

class BaseResponse<T> {
    @SerializedName("resp_code") private val resCode: Int? = null
    val code: Int? = null
    val message: String? = null
    val data: T? = null
}