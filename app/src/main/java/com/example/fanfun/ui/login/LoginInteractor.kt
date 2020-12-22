package com.example.fanfun.ui.login

import android.util.Base64
import android.util.Log
import com.example.fanfun.network.LoginResponse
import com.example.fanfun.network.NetworkManager
import com.example.fanfun.network.Result
import com.example.fanfun.network.TokenBody
import com.example.fanfun.utils.HAWK_USER_AUD
import com.google.gson.Gson
import com.orhanobut.hawk.Hawk

class LoginInteractor(val intOut: LoginContract.InteractorOutput): LoginContract.Interactor {

    override fun doLogin(email: String, password: String){
        NetworkManager.userLogin(email,password, object: Result<LoginResponse>{

            override fun onSuccess(response: LoginResponse) {
                handleToken(response.accessToken)
                intOut.onLoginSuccess()
            }
            override fun onError(code: Int, message: String) {
                intOut.onError()
            }

            override fun onFailure(message: String) {
                intOut.onError()
            }
        })
    }

    private fun handleToken(token: String) {

        val split = token.split(".")[1]
        val data = Base64.decode(split, Base64.DEFAULT)
        val jsonData = String(data, charset("UTF-8"))
        val tokenBody: TokenBody = Gson().fromJson(jsonData,TokenBody::class.java)
        Hawk.put(HAWK_USER_AUD, tokenBody.aud)

    }

}