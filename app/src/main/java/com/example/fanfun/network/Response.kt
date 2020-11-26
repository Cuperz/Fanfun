package com.example.fanfun.network

import com.google.gson.annotations.SerializedName

class BaseResponse<T> {
    @SerializedName("resp_code") private val resCode: Int? = null
    val code: Int? = null
    val message: String? = null
    val data: T? = null
}

class LoginResponse{
    val code: String? = null
    val message: String? = null
    @SerializedName("access_token") val accessToken: String? = null
    @SerializedName("token_type") val tokenType: String? = null
    @SerializedName("expires_in") val expiresIn: Int? = null
}