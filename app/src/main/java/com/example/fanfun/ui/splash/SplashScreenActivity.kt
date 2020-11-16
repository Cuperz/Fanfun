package com.example.fanfun.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fanfun.ui.camera.CameraActivity
import com.example.fanfun.ui.login.LoginActivity


class SplashScreenActivity:AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(Intent(this , LoginActivity::class.java))
        //startActivity(Intent(this , CameraActivity::class.java))
        finish()
    }

}