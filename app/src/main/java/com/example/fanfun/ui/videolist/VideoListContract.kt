package com.example.fanfun.ui.videolist

import com.example.fanfun.model.Request
import java.util.ArrayList

interface VideoListContract {

    interface View{
        fun videoDeleted(userVideos: ArrayList<String>?, position: Int)
        fun userDeleted()

    }

    interface Presenter{
        fun getVideos(requestId: String): ArrayList<String>
        fun toWatchVideo(path: String, request: Request)
        fun deleteVideo(requestId: String, path: String, position: Int)
        fun toHome()
        fun getPhoto(): String?

    }

    interface Router{
        fun toWatchVideo(path: String, request: Request)
        fun toHome()
    }

    interface Interactor{
        fun getVideos(requestId: String): ArrayList<String>
        fun deleteVideo(requestId: String, path: String, position: Int)
        fun getPhoto(): String?

    }

    interface InteractorOutput{
        fun videoDeleted(userVideos: ArrayList<String>?, position: Int)
        fun userDeleted()

    }
}