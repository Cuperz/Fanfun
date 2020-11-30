package com.example.fanfun.network

import com.example.fanfun.model.Model
import com.example.fanfun.utils.User
import com.google.gson.annotations.SerializedName

class BaseResponse {
    @SerializedName("resp_code") private val resCode: Int? = null
    val code: Int? = null
    val message: String? = null
}

class LoginResponse{
    val code: String? = null
    val message: String? = null
    @SerializedName("access_token") val accessToken: String? = null
    @SerializedName("token_type") val tokenType: String? = null
    @SerializedName("expires_in") val expiresIn: Int? = null
}

class VideoListResponse{
    val limit: Int? = null
    val offset: Int? = null
    val total: Int? = null
    val code: Int? = null
    val message: String? = null
    val data: ArrayList<Model.Request>? = null
}

class VideoDataResponse{
    val idRequest: Int? = null
    val idUser: Int? = null
    val nameUser: String? = null
    val photoUser: String? = null
    val idFamous: Int? = null
    val typeMessage: String? = null
    val message: String? = null
    val limitDate: String? = null
    val state: String? = null
}

class ProfileResponse{
    val name: String? = null
    val email: String? = null
    val photo: String? = null
}