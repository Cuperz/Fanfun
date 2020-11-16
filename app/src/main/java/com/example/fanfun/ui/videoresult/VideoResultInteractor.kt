package com.example.fanfun.ui.videoresult

class VideoResultInteractor(var intOut: VideoResultContract.InterawctorOutput): VideoResultContract.Interactor {

    override fun sendVideo(mVideoFile: String?) {
        intOut.onVideoSent()
    }

}