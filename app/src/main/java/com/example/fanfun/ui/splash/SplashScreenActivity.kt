package com.example.fanfun.ui.splash

import android.content.Intent
import android.os.Bundle
import com.example.fanfun.ui.home.HomeActivity
import com.example.fanfun.ui.login.LoginActivity
import com.example.fanfun.utils.App
import com.example.fanfun.utils.HAWK_USER_ID
import com.example.fanfun.utils.forwardTransition
import com.orhanobut.hawk.Hawk


class SplashScreenActivity: App() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkToken()
    }

    private fun checkToken() {
        if (Hawk.contains(HAWK_USER_ID)){
            startActivity(Intent(this , HomeActivity::class.java))
        }
        else{
            startActivity(Intent(this , LoginActivity::class.java))
        }
        finish()
        forwardTransition()
    }

}