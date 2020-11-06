package com.example.fanfun.ui.home

import android.content.Intent
import com.example.fanfun.ui.profile.ProfileActivity

class HomeRouter(var activity: HomeActivity):HomeContract.Router {

    override fun toProfile() {
        val intent = Intent(activity, ProfileActivity::class.java)
        activity.startActivity(intent)
    }
}