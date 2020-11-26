package com.example.fanfun.network

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query


data class LoginRequest(
    var email: String? = null,
    var password: String? = null,
    @SerializedName("grant_type") val grantType: String = "password",
    @SerializedName("client_id") val clientId: String = "e7377c48-9911-4341-82df-caa58b021c99",
    @SerializedName("client_secret") val clientSecret: String = "Y5MsLoeyzsTYLTEYhedmCXHnWJl83PIc43PPyUal")

interface API{

    @POST("users/login")
    fun userLogin(@Body baseRequest: LoginRequest): Call<LoginResponse>

}

interface Result<T>{

    fun onSuccess(response: T )
    fun onError(code: Int, message: String)
    fun onFailure(message: String)
}