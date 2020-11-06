package com.example.fanfun.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fanfun.ui.home.HomeActivity
import com.example.fanfun.ui.login.LoginActivity
import com.example.fanfun.ui.profile.ProfileActivity


class SplashScreenActivity:AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(Intent(this , LoginActivity::class.java))
        finish()
    }

}