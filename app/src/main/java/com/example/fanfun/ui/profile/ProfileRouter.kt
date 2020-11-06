package com.example.fanfun.ui.profile

import android.content.Intent
import com.example.fanfun.ui.login.LoginActivity

class ProfileRouter(val activity: ProfileActivity):ProfileContract.Router {

    override fun toLogin() {
        val intent = Intent(activity, LoginActivity::class.java)
        activity.startActivity(intent)
        activity.finishAffinity()
    }
}