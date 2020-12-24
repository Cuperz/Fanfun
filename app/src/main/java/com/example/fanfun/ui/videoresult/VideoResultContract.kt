package com.example.fanfun.ui.videoresult

import com.example.fanfun.model.Request

interface VideoResultContract {

    interface View{
        fun videoFailed()

    }

    interface Presenter{
        fun toCamera(request: Request)
        fun sendVideo(requestId: String,videoFile: String)
        fun toHome()
        fun deleteVideo(request: Request, videoFrom: Int, videoFile: String?)

    }

    interface Router{
        fun toCamera(request: Request)
        fun toSuccess()
        fun toHome()
        fun toVideoList(userId: String)

    }

    interface Interactor{
        fun sendVideo(requestId: String,videoFile: String)
        fun deleteVideo(request: Request, videoFrom: Int, videoFile: String?)

    }

    interface InteractorOutput{
        fun onVideoSent()
        fun videoDeleted(videoFrom: Int, request: Request)
        fun videoFailed()

    }
}