package com.example.fanfun.ui.videoresult

interface VideoResultContract {

    interface View{

    }

    interface Presenter{
        fun toCamera()
        fun sendVideo(mVideoFile: String?)
        fun toHome()

    }

    interface Router{
        fun toCamera()
        fun toSuccess()
        fun toHome()

    }

    interface Interactor{
        fun sendVideo(mVideoFile: String?)

    }

    interface InterawctorOutput{
        fun onVideoSent()

    }
}