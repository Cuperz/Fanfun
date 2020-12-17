package com.example.fanfun.ui.login

import com.example.fanfun.network.LoginResponse
import com.example.fanfun.network.NetworkManager
import com.example.fanfun.network.Result
import com.example.fanfun.utils.HAWK_USER_ID
import com.example.fanfun.utils.HAWK_USER_TOKEN
import com.orhanobut.hawk.Hawk
import retrofit2.Response

class LoginInteractor(val intOut: LoginContract.InteractorOutput): LoginContract.Interactor {

    override fun doLogin(email: String, password: String){
        NetworkManager.userLogin(email,password, object: Result<LoginResponse>{

            override fun onSuccess(response: LoginResponse) {
                Hawk.put(HAWK_USER_TOKEN, response.accessToken)
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

}