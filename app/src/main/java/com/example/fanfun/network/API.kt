package com.example.fanfun.network

import com.google.gson.annotations.SerializedName
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*


data class LoginRequest(
    val email: String? = null,
    val password: String? = null,
    val scope: String = "famous",
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

    @GET("famous/{id}")
    fun getRequestList(@Path("id") userId: String ): Call<RequestListResponse>

    @GET("famous/{id}")
    fun getProfile(@Path("id") userId: String ): Call<ProfileResponse>

    @POST("videos/{request_id}/send")
    fun uploadVideo(@Body requestBody: RequestBody, @Path("request_id") requestId: String): Call<BaseResponse>

}

interface Result<T>{

    fun onSuccess(response: T )
    fun onError(code: Int, message: String)
    fun onFailure(message: String)
}