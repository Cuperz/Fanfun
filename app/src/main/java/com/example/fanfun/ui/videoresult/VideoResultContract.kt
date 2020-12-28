package com.example.fanfun.ui.videoresult

import com.example.fanfun.model.Request

interface VideoResultContract {

    interface View{
        fun videoFailed()

    }

    interface Presenter{
        fun toCamera(request: Request)
        fun sendVideo(request: Request,videoFile: String)
        fun toHome()
        fun deleteVideo(request: Request, videoFrom: Int, videoFile: String?)

    }

    interface Router{
        fun toCamera(request: Request)
        fun toSuccess(request: Request, videoFile: String)
        fun toHome()
        fun toVideoList(request: Request)

    }

    interface Interactor{
        fun sendVideo(request: Request,videoFile: String)
        fun deleteVideo(request: Request, videoFrom: Int, videoFile: String?)

    }

    interface InteractorOutput{
        fun onVideoSent(request: Request, videoFile: String)
        fun videoDeleted(videoFrom: Int, request: Request)
        fun videoFailed()

    }
}