package com.example.fanfun.ui.camera

import java.io.File

interface CameraContract {

    interface View{

    }

    interface Presenter{
        fun sendVideo(userId: String,mVideoPath: String?)
        fun toHome()
    }

    interface Router{
        fun sendVideo(userId: String, mVideoPath: String?)
        fun toHome()
    }

    interface Interactor{

    }

    interface InteractorOutput{

    }
}