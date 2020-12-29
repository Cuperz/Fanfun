package com.example.fanfun.network

import com.example.fanfun.utils.*
import com.orhanobut.hawk.Hawk
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

    fun verifyToken(result: Result<BaseResponse>){
        mAPi.verifyToken("Bearer "+ Hawk.get(HAWK_USER_TOKEN)).enqueue(object : Callback<BaseResponse>{
            override fun onResponse(call: Call<BaseResponse>, response: Response<BaseResponse>) {

            }

            override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
            }
        })
    }

    /** Return data from the app user */
    fun getProfile(result : Result<ProfileResponse>){
        mAPi.getProfile(Hawk.get(HAWK_USER_ID)).enqueue(object : Callback<ProfileResponse>{
            override fun onResponse(call: Call<ProfileResponse>, response: Response<ProfileResponse>) {
                if(response.isSuccessful){
                    result.onSuccess(response.body()!!)
                }else{
                    result.onError(response.code(), response.message())
                }
            }

            override fun onFailure(call: Call<ProfileResponse>, t: Throwable) {
                result.onFailure(t.message!!)
            }
        })
    }


    /** Return the data from the famous using the app */
    fun getRequestList(result: Result<RequestListResponse>){
        mAPi.getRequestList(Hawk.get(HAWK_USER_ID)).enqueue(object : Callback<RequestListResponse>{
            override fun onResponse(call: Call<RequestListResponse>, response: Response<RequestListResponse>) {
                if(response.isSuccessful){
                    result.onSuccess(response.body()!!)
                }else{
                    result.onError(response.code(), response.message())
                }
            }

            override fun onFailure(call: Call<RequestListResponse>, t: Throwable) {
                result.onFailure(t.message!!)
            }

        })
    }

}