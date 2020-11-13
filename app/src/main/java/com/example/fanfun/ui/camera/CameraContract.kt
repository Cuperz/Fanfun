package com.example.fanfun.ui.camera

interface CameraContract {

    interface View{

    }

    interface Presenter{
        fun sendVideo(mVideoPath: String?)

    }

    interface Router{
        fun sendVideo(mVideoPath: String?)

    }

    interface Interactor{

    }

    interface InteractorOutput{

    }
}