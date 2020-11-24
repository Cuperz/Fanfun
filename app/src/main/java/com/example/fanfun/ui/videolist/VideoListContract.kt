package com.example.fanfun.ui.videolist

import java.util.ArrayList

interface VideoListContract {

    interface View{
        fun videoDeleted(userVideos: ArrayList<String>?)
        fun userDeleted()

    }

    interface Presenter{
        fun getVideos(userId: String): ArrayList<String>
        fun toWatchVideo(path: String)
        fun deleteVideo(userId: String, path: String)
        fun toHome()

    }

    interface Router{
        fun toWatchVideo(path: String)
        fun toHome()
    }

    interface Interactor{
        fun getVideos(userId: String): ArrayList<String>
        fun deleteVideo(userId: String, path: String)

    }

    interface InteractorOutput{
        fun videoDeleted(userVideos: ArrayList<String>?)
        fun userDeleted()

    }
}