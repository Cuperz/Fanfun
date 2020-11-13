package com.example.fanfun.ui.videoresult

interface VideoResultContract {

    interface View{

    }

    interface Presenter{
        fun toCamera()
        fun sendVideo(mVideoFile: String?)

    }

    interface Router{
        fun toCamera()
        fun toSuccess()

    }

    interface Interactor{
        fun sendVideo(mVideoFile: String?)

    }

    interface InterawctorOutput{

    }
}