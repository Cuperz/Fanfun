package com.example.fanfun.ui.videoresult

interface VideoResultContract {

    interface View{

    }

    interface Presenter{
        fun toCamera()
        fun sendVideo(videoFile: String)
        fun toHome()
        fun deleteVideo(userId: String, videoFrom: Int, videoFile: String?)

    }

    interface Router{
        fun toCamera()
        fun toSuccess()
        fun toHome()
        fun toVideoList(userId: String)

    }

    interface Interactor{
        fun sendVideo(videoFile: String)
        fun deleteVideo(userId: String, videoFrom: Int, videoFile: String?)

    }

    interface InteractorOutput{
        fun onVideoSent()
        fun videoDeleted(videoFrom: Int, userId: String)

    }
}