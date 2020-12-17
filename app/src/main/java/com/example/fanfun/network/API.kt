package com.example.fanfun.network

import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.http.*


data class LoginRequest(
    val email: String? = null,
    val password: String? = null,
    val scope: String = "user",
    @SerializedName("grant_type") val grantType: String = "password",
    @SerializedName("client_id") val clientId: String = "1231c2f5-5e26-4f42-a70f-90dbc781113e",
    @SerializedName("client_secret") val clientSecret: String = "jd_HblXXzKd4cZo")

data class VideoRequest(
        val idRequest: Int? = null,
        val idUser: Int? = null,
        val idFamous: Int? = null,
        val urlVideo: String? = null
)

interface API{

    @POST("oauth/token")
    fun userLogin(@Body baseRequest: LoginRequest): Call<LoginResponse>

    @GET("token/verify")
    fun verifyToken(@Header("Authorization") token: String): Call<BaseResponse>

    @GET("videos")
    fun getVideos(@Header("Authorization") token: String): Call<VideoListResponse>

    @GET("videos/{id}")
    fun getVideoData(@Header("Authorization") token: String, @Path("id") clientId:Int?): Call<VideoDataResponse>

    @POST("videos/send")
    fun senVideo(@Header("Authorization") token: String ,@Body videoRequest: VideoRequest): Call<BaseResponse>

    @GET("famous/{id}")
    fun getProfile(@Header("Authorization") token: String, @Path("id") userId:Int? ): Call<ProfileResponse>

}

interface Result<T>{

    fun onSuccess(response: T )
    fun onError(code: Int, message: String)
    fun onFailure(message: String)
}