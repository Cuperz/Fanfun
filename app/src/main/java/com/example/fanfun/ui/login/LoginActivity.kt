package com.example.fanfun.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fanfun.R
import com.example.fanfun.utils.bind
import com.google.android.material.button.MaterialButton

class LoginActivity : AppCompatActivity(), LoginContract.View {

    val mLoginButton: MaterialButton by bind(R.id.login_button)
    var mPresenter: LoginContract.Presenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mPresenter = LoginPresenter(this)
        mLoginButton.setOnClickListener {onLogin()}
    }

    private fun onLogin(){
            mPresenter?.onLogin()
    }
}
