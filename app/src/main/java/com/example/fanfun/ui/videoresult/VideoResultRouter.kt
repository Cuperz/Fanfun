package com.example.fanfun.ui.videoresult

import android.content.Intent
import com.example.fanfun.model.Request
import com.example.fanfun.ui.camera.CameraActivity
import com.example.fanfun.ui.home.HomeActivity
import com.example.fanfun.ui.success.SuccessActivity
import com.example.fanfun.ui.videolist.VideoListActivity
import com.example.fanfun.ui.videoupload.VideoUploadActivity
import com.example.fanfun.utils.*

class VideoResultRouter( var activity: VideoResultActivity): VideoResultContract.Router {

    override fun toCamera(request: Request) {
        val intent = Intent(activity, CameraActivity::class.java)
        intent.putExtra("request", request.toJson())
        activity.startActivity(intent)
        activity.finishAffinity()
        activity.backwardTransition()
    }

    override fun toUpload(request: Request, videoFile: String) {
        val intent = Intent(activity, VideoUploadActivity::class.java)
        intent.putExtra("request",request.toJson())
        intent.putExtra("path",videoFile)
        intent.putExtra("result", FROM_SUCCESS)
        activity.startActivity(intent)
        activity.finishAffinity()
        activity.forwardTransition()
    }

    override fun toHome() {
        val intent = Intent(activity, HomeActivity::class.java)
        activity.startActivity(intent)
        activity.finishAffinity()
        activity.backwardTransition()
    }

    override fun toVideoList(request: Request) {
        val intent = Intent(activity, VideoListActivity::class.java)
        intent.putExtra("request",request.toJson())
        intent.putExtra("from", FROM_RESULT)
        activity.startActivity(intent)
        activity.finishAffinity()
        activity.backwardTransition()
    }
}