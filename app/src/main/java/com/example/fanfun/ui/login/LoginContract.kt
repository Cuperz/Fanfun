package com.example.fanfun.ui.login

interface LoginContract {

    interface View{
    }

    interface Presenter{
        fun onLogin()
    }

    interface Interactor{

    }

    interface InteractorOutput{

    }

    interface Router{
        fun toHome()

    }
}