package com.example.fanfun.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fanfun.R

class LoginActivity : AppCompatActivity(), LoginContract.View {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val mPresenter: LoginContract.Presenter = LoginPresenter(this)
    }
}