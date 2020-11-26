package com.example.fanfun.network

import com.example.fanfun.utils.ErrorHandler
import com.example.fanfun.utils.SuccessHandler
import com.example.fanfun.utils.queue
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object NetworkManager {

    private val mAPi: API by lazy { RestClient.instanceAPI }


    fun userLogin(email: String, password: String, result: Result<LoginResponse>) {
        val loginRequest = LoginRequest(email, password)
        mAPi.userLogin(loginRequest).enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful){
                    result.onSuccess(response.body()!!)
                }else{
                    result.onError(response.code(), response.message())
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                result.onFailure(t.message!!)
            }
        })
    }


    fun userLogin2(email: String, password: String ,onSuccess: SuccessHandler<LoginResponse>, onError: ErrorHandler?) {
        val loginRequest = LoginRequest(email = email, password = password)
        mAPi.userLogin(loginRequest).queue({
            onSuccess(it)
        }, onError)
    }
}