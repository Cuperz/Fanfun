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

data class VideoDataResponse(
    val idRequest: Int? = null,
    val idUser: Int? = null,
    val nameUser: String? = null,
    val photoUser: String? = null,
    val idFamous: Int? = null,
    val typeMessage: String? = null,
    val message: String? = null,
    val limitDate: String? = null,
    val state: String? = null
)

data class ProfileResponse(
    val name: String? = null,
    val email: String? = null,
    val photo: String? = null
)