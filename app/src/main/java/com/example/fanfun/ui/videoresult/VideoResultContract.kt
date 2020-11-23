package com.example.fanfun.ui.videoresult

interface VideoResultContract {

    interface View{

    }

    interface Presenter{
        fun toCamera()
        fun sendVideo(videoFile: String)
        fun toHome()

    }

    interface Router{
        fun toCamera()
        fun toSuccess()
        fun toHome()

    }

    interface Interactor{
        fun sendVideo(videoFile: String)

    }

    interface InterawctorOutput{
        fun onVideoSent()

    }
}