package com.example.fanfun.ui.videoresult

import java.io.File

class VideoResultInteractor(var intOut: VideoResultContract.InterawctorOutput): VideoResultContract.Interactor {

    override fun sendVideo(videoFile: String) {
        File(videoFile).delete()
        intOut.onVideoSent()
    }

}