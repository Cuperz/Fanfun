package com.example.fanfun.ui.home

interface HomeContract {

    interface View{

    }

    interface Presenter{
        fun toProfile()
        fun getPhoto(): String?

    }

    interface Router{
        fun toProfile()

    }

    interface Interactor{
        fun getPhoto(): String?

    }

    interface InteractorOutput{
        
    }
}