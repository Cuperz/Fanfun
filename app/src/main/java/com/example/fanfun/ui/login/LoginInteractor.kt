package com.example.fanfun.ui.login

import com.example.fanfun.network.LoginResponse
import com.example.fanfun.network.NetworkManager
import com.example.fanfun.network.Result
import retrofit2.Response

class LoginInteractor(val intOut: LoginContract.InteractorOutput): LoginContract.Interactor {

    fun doLogin(email: String, password: String){
        NetworkManager.userLogin(email,password, object: Result<LoginResponse>{

            override fun onSuccess(response: LoginResponse) {
                val token = response.accessToken
            }
            override fun onError(code: Int, message: String) {
            }

            override fun onFailure(message: String) {
            }
        })
    }

    fun doLogin2(email: String, password: String){
        NetworkManager.userLogin2(email, password,{

        },{ code , _ ->

        })
    }
}