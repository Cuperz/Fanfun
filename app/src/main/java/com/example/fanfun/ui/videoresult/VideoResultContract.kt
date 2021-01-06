package com.example.fanfun.ui.videoresult

import com.example.fanfun.model.Request

interface VideoResultContract {

    interface View{

    }

    interface Presenter{
        fun toCamera(request: Request)
        fun sendVideo(request: Request,videoFile: String)
        fun toHome()
        fun deleteVideo(request: Request, videoFrom: Int, videoFile: String?)

    }

    interface Router{
        fun toCamera(request: Request)
        fun toUpload(request: Request, videoFile: String)
        fun toHome()
        fun toVideoList(request: Request)
    }

    interface Interactor{
        fun deleteVideo(request: Request, videoFrom: Int, videoFile: String?)

    }

    interface InteractorOutput{
        fun videoDeleted(videoFrom: Int, request: Request)

    }
}