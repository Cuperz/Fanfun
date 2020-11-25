package com.example.fanfun.ui.videoresult

import com.example.fanfun.utils.FROM_CAMERA
import com.example.fanfun.utils.FROM_SKETCH
import com.example.fanfun.utils.deleteUserVideo
import java.io.File

class VideoResultInteractor(var intOut: VideoResultContract.InteractorOutput): VideoResultContract.Interactor {

    override fun sendVideo(videoFile: String) {
        File(videoFile).delete()
        intOut.onVideoSent()
    }

    override fun deleteVideo(userId: String, videoFrom: Int, videoFile: String?) {
        when (videoFrom){
            FROM_CAMERA -> File(videoFile!!).delete()
            FROM_SKETCH -> deleteUserVideo(userId, videoFile!!)
            else -> return
        }
        intOut.videoDeleted(videoFrom,userId)
    }

}