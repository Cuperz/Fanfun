package com.example.fanfun.ui.videoresult


import com.example.fanfun.model.Request
import com.example.fanfun.utils.*
import java.io.File

class VideoResultInteractor(var intOut: VideoResultContract.InteractorOutput,val  activity: VideoResultActivity): VideoResultContract.Interactor {

    override fun deleteVideo(request: Request, videoFrom: Int, videoFile: String?) {
        when (videoFrom){
            FROM_CAMERA -> File(videoFile!!).delete()
            FROM_SKETCH -> deleteUserVideo(request.id, videoFile!!)
            else -> return
        }
        intOut.videoDeleted(videoFrom,request)
    }

}