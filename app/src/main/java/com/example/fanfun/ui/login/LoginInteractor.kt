package com.example.fanfun.ui.login

import android.util.Base64
import com.example.fanfun.network.*
import com.example.fanfun.utils.HAWK_USER_ID
import com.example.fanfun.utils.HAWK_USER_PROFILE
import com.google.gson.Gson
import com.orhanobut.hawk.Hawk

class LoginInteractor(val intOut: LoginContract.InteractorOutput): LoginContract.Interactor {

    override fun doLogin(email: String, password: String){
        NetworkManager.userLogin(email,password, object: Result<LoginResponse>{

            override fun onSuccess(response: LoginResponse) {
                handleToken(response.accessToken)
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
        Hawk.put(HAWK_USER_ID, tokenBody.sub)
        saveProfileInfo()
    }

    private fun saveProfileInfo() {
        NetworkManager.getProfile( object : Result<ProfileResponse>{
            override fun onSuccess(response: ProfileResponse) {
                Hawk.put(HAWK_USER_PROFILE, response)
                intOut.onLoginSuccess()
            }

            override fun onError(code: Int, message: String) {

            }

            override fun onFailure(message: String) {

            }
        })
    }

}