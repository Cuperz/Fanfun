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

    /** Returns all pending and sent videos from the famous**/
    fun getVideos(result: Result<VideoListResponse>){
        mAPi.getVideos("Bearer "+ Hawk.get(HAWK_USER_TOKEN)).enqueue(object : Callback<VideoListResponse>{
            override fun onResponse(call: Call<VideoListResponse>, response: Response<VideoListResponse>) {

            }

            override fun onFailure(call: Call<VideoListResponse>, t: Throwable) {

            }
        })
    }

    /** Returns the information of 1 video request**/
    fun getVideoData(result: Result<VideoDataResponse>){
        mAPi.getVideoData("Bearer "+ Hawk.get(HAWK_USER_TOKEN), Hawk.get(HAWK_USER_ID)).enqueue(object : Callback<VideoDataResponse>{
            override fun onResponse(call: Call<VideoDataResponse>, response: Response<VideoDataResponse>) {
            }

            override fun onFailure(call: Call<VideoDataResponse>, t: Throwable) {
            }
        })
    }

    fun sendVideo(videoRequest: VideoRequest ,result: Result<BaseResponse>){
        mAPi.senVideo("Bearer "+ Hawk.get(HAWK_USER_TOKEN), videoRequest).enqueue(object : Callback<BaseResponse>{
            override fun onResponse(call: Call<BaseResponse>, response: Response<BaseResponse>) {

            }

            override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
            }
        })
    }

    /** Return the data from the famous using the app */
    fun getProfile(result: Result<ProfileResponse>){
        mAPi.getProfile("Bearer "+ Hawk.get(HAWK_USER_TOKEN), Hawk.get(HAWK_USER_ID)).enqueue(object : Callback<ProfileResponse>{
            override fun onResponse(call: Call<ProfileResponse>, response: Response<ProfileResponse>) {

            }

            override fun onFailure(call: Call<ProfileResponse>, t: Throwable) {

            }

        })
    }


    /*fun userLogin2(email: String, password: String ,onSuccess: SuccessHandler<LoginResponse>, onError: ErrorHandler?) {
        val loginRequest = LoginRequest(email = email, password = password)
        mAPi.userLogin(loginRequest).queue({
            onSuccess(it)
        }, onError)
    }*/
}