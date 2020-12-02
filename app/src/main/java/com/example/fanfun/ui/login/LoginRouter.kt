package com.example.fanfun.ui.login

import android.content.Intent
import android.net.Uri
import com.example.fanfun.ui.home.HomeActivity
import com.example.fanfun.utils.forwardTransition

class LoginRouter(val activity: LoginActivity): LoginContract.Router {

    override fun toHome() {
        val intent = Intent(activity,HomeActivity::class.java)
        activity.startActivity(intent)
        activity.finishAffinity()
        activity.forwardTransition()
    }

    override fun toWebLink() {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse("https://facebook.com")
        activity.startActivity(intent)
    }
}