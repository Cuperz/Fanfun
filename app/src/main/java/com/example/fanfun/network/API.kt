package com.example.fanfun.network

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*


data class LoginRequest(
    val email: String? = null,
    val password: String? = null,
    @SerializedName("grant_type") val grantType: String = "password",
    @SerializedName("client_id") val clientId: String = "e7377c48-9911-4341-82df-caa58b021c99",
    @SerializedName("client_secret") val clientSecret: String = "Y5MsLoeyzsTYLTEYhedmCXHnWJl83PIc43PPyUal")

data class VideoRequest(
        val idRequest: Int? = null,
        val idUser: Int? = null,
        val idFamous: Int? = null,
        val urlVideo: String? = null
)

interface API{

    @POST("users/login")
    fun userLogin(@Body baseRequest: LoginRequest): Call<LoginResponse>

    @GET("videos")
    fun getVideos(@Header("Authorization") token: String): Call<VideoListResponse>

    @GET("videos/{id}")
    fun getVideoRequest(@Header("Authorization") token: String ,@Path("id") id:Int?): Call<VideoRequestResponse>

    @POST("videos/send")
    fun senVideo(@Header("Authorization") token: String ,@Body videoRequest: VideoRequest): Call<BaseResponse>

}

interface Result<T>{

    fun onSuccess(response: T )
    fun onError(code: Int, message: String)
    fun onFailure(message: String)
}