package com.example.fanfun.ui.profile

interface ProfileContract {

    interface View{

    }

    interface Presenter{
        fun doLogOut()

    }

    interface Interactor{
        fun doLogOut()

    }

    interface InteractorOutput{
    }

    interface Router{
        fun toLogin()

    }
}