package com.example.fanfun.ui.camera

interface CameraContract {

    interface View{

    }

    interface Presenter{
        fun sendVideo(mVideoPath: String?)
        fun toHome()

    }

    interface Router{
        fun sendVideo(mVideoPath: String?)
        fun toHome()

    }

    interface Interactor{

    }

    interface InteractorOutput{

    }
}