package com.example.fanfun.ui.videolist

import java.util.ArrayList

interface VideoListContract {

    interface View{

    }

    interface Presenter{
        fun getVideos(userId: String): ArrayList<String>
        fun toWatchVideo(path: String)
        fun deleteVideo(userId: String, path: String)

    }

    interface Router{
        fun toWatchVideo(path: String)

    }

    interface Interactor{
        fun getVideos(userId: String): ArrayList<String>
        fun deleteVideo(userId: String, path: String)

    }

    interface InteractorOutput{

    }
}