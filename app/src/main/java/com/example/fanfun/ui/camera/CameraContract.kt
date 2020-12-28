package com.example.fanfun.ui.camera

import com.example.fanfun.model.Request

interface CameraContract {

    interface View{

    }

    interface Presenter{
        fun sendVideo(request: Request ,mVideoPath: String?)
        fun toHome()
    }

    interface Router{
        fun sendVideo(request: Request , mVideoPath: String?)
        fun toHome()
    }

    interface Interactor{

    }

    interface InteractorOutput{

    }
}