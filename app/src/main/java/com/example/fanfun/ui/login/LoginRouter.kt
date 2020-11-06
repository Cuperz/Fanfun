package com.example.fanfun.ui.login

import android.content.Intent
import com.example.fanfun.ui.home.HomeActivity

class LoginRouter(val activity: LoginActivity): LoginContract.Router {

    override fun toHome() {
        val intent = Intent(activity,HomeActivity::class.java)
        activity.startActivity(intent)
        activity.finishAffinity()
    }
}