package com.example.fanfun.ui.success

import android.content.Intent
import com.example.fanfun.ui.home.HomeActivity
import com.example.fanfun.utils.backwardTransition

class SuccessRouter(var activity: SuccessActivity):SuccessContract.Router {

    override fun toHome() {
        val intent = Intent(activity, HomeActivity::class.java)
        activity.startActivity(intent)
        activity.finishAffinity()
        activity.backwardTransition()
    }
}