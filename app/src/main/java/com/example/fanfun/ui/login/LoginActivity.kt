package com.example.fanfun.ui.login

import android.os.Bundle
import androidx.core.content.res.ResourcesCompat
import com.example.fanfun.R
import com.example.fanfun.utils.App
import com.example.fanfun.utils.bind
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputLayout

class LoginActivity : App(), LoginContract.View {

    val mLoginButton: MaterialButton by bind(R.id.login_button)
    val mRegisterButton: MaterialButton by bind(R.id.register_button)
    val mEmailInput: TextInputLayout by bind(R.id.email_input)
    var mPresenter: LoginContract.Presenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mPresenter = LoginPresenter(this)
        mEmailInput.typeface = ResourcesCompat.getFont(this ,R.font.montserrat_bold)
        mLoginButton.setOnClickListener {onLogin()}
        mRegisterButton.setOnClickListener { onRegister() }
    }

    private fun onRegister() {

    }

    private fun onLogin(){
            mPresenter?.onLogin()
    }
}
