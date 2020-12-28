package com.example.fanfun.ui.profile

interface ProfileContract {

    interface View{
        fun setData(name: String, email: String, photo: String?)
    }

    interface Presenter{
        fun doLogOut()
        fun getInfo()

    }

    interface Interactor{
        fun doLogOut()
        fun getInfo()

    }

    interface InteractorOutput{
        fun setData(name: String, email: String, photo: String?)
    }

    interface Router{
        fun toLogin()

    }
}