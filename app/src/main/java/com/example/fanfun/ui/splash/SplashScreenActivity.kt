package com.example.fanfun.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fanfun.ui.camera.CameraActivity
import com.example.fanfun.ui.login.LoginActivity
import com.example.fanfun.utils.App
import com.example.fanfun.utils.forwardTransition


class SplashScreenActivity: App() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(Intent(this , LoginActivity::class.java))
        finish()
        forwardTransition()
    }

}