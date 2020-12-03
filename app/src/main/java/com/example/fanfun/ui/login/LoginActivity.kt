package com.example.fanfun.ui.login

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import com.example.fanfun.R
import com.example.fanfun.utils.App
import com.example.fanfun.utils.bind
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputLayout
import com.orhanobut.hawk.Hawk

class LoginActivity : App(), LoginContract.View {

    private val mLoginButton: MaterialButton by bind(R.id.login_button)
    private val mRegisterButton: MaterialButton by bind(R.id.register_button)
    private val mEmailInput: TextInputLayout by bind(R.id.email_input)
    private val mPasswordInput: TextInputLayout by bind(R.id.password_input)
    private val mErrorText: TextView by bind(R.id.login_error_message)
    private val mWebLink: TextView by bind(R.id.login_funfun_link)
    var mPresenter: LoginContract.Presenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mPresenter = LoginPresenter(this)
        mEmailInput.typeface = ResourcesCompat.getFont(this ,R.font.montserrat_bold)
        mPasswordInput.typeface = ResourcesCompat.getFont(this ,R.font.montserrat_bold)
        mLoginButton.setOnClickListener {onLogin()}
        mRegisterButton.setOnClickListener { onRegister() }
        mWebLink.setOnClickListener { mPresenter?.toWebLink() }
    }

    private fun onRegister() {
    }

    private fun onLogin(){
        mLoginButton.isEnabled = false
        mErrorText.visibility = View.GONE
        val email: String = mEmailInput.editText?.text.toString()
        val password: String = mPasswordInput.editText?.text.toString()
        //mPresenter?.validateLogin(email, password)
        mPresenter?.onLogin()
    }

    override fun onLoginError() {
        mLoginButton.isEnabled = true
        mErrorText.visibility = View.VISIBLE
        mEmailInput.error = "Error"
        mPasswordInput.error = "Error"
    }
}
