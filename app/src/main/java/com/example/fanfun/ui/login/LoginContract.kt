package com.example.fanfun.ui.login

interface LoginContract {

    interface View{
        fun onLoginError()
    }

    interface Presenter{
        fun onLogin()
        fun validateLogin(email: String, password: String)
        fun toWebLink()
    }

    interface Interactor{
        fun doLogin(email: String, password: String)
    }

    interface InteractorOutput{
        fun onError()
        fun onLoginSuccess()

    }

    interface Router{
        fun toHome()
        fun toWebLink()

    }
}