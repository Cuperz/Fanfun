package com.example.fanfun.ui.profile

import android.content.Intent
import android.net.Uri
import com.example.fanfun.ui.login.LoginActivity
import com.example.fanfun.utils.TERMS_N_CONDITIONS
import com.example.fanfun.utils.backwardTransition

class ProfileRouter(val activity: ProfileActivity):ProfileContract.Router {

    override fun toLogin() {
        val intent = Intent(activity, LoginActivity::class.java)
        activity.startActivity(intent)
        activity.finishAffinity()
        activity.backwardTransition()
    }

    override fun toTyc() {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(TERMS_N_CONDITIONS)
        activity.startActivity(intent)
    }
}