package com.example.fanfun.ui.videolist


import com.example.fanfun.utils.deleteUserVideo
import com.example.fanfun.utils.getUserVideos
import java.util.ArrayList

class VideoListInteractor(val intOut: VideoListContract.InteractorOutput): VideoListContract.Interactor {

    override fun getVideos(userId: String): ArrayList<String> {
        return getUserVideos(userId)!!
    }

    override fun deleteVideo(userId: String, path: String) {
        deleteUserVideo(userId,path)
    }
}