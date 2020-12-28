package com.example.fanfun.network

import com.example.fanfun.model.Request
import com.google.gson.annotations.SerializedName

data class BaseResponse (
    @SerializedName("resp_code") private val resCode: Int? = null,
    val code: Int? = null,
    val message: String? = null
)

data class LoginResponse(
    @SerializedName("access_token") val accessToken: String,
    @SerializedName("token_type") val tokenType: String,
    @SerializedName("expires_in") val expiresIn: String,
    @SerializedName("refresh_token") val refreshToken: String? = null,
    val scope: String? = null
)

data class VideoListResponse(
        val limit: Int? = null,
        val offset: Int? = null,
        val total: Int? = null,
        val code: Int? = null,
        val message: String? = null,
        val data: ArrayList<Request>? = null
)

data class RequestListResponse(
        val nameUser: String,
        val lastName: String,
        val email: String,
        val videos: ArrayList<Request>? = null
)

data class ProfileResponse(
    val name: String,
    val lastName: String? = null,
    val email: String? = null,
    val photo: String? = null
)

data class TokenBody(
        val iss: String,
        val aud: String,
        val jti: String,
        val iat: Float,
        val exp: Float,
        val sub: String? = null,
        val scope: String? = null
)