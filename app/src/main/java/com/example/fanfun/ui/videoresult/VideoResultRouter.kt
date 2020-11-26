package com.example.fanfun.ui.videoresult

import android.content.Intent
import com.example.fanfun.ui.camera.CameraActivity
import com.example.fanfun.ui.home.HomeActivity
import com.example.fanfun.ui.success.SuccessActivity
import com.example.fanfun.ui.videolist.VideoListActivity
import com.example.fanfun.utils.backwardTransition
import com.example.fanfun.utils.forwardTransition

class VideoResultRouter( var activity: VideoResultActivity): VideoResultContract.Router {

    override fun toCamera() {
        val intent = Intent(activity, CameraActivity::class.java)
        activity.startActivity(intent)
        activity.finishAffinity()
        activity.backwardTransition()
    }

    override fun toSuccess() {
        val intent = Intent(activity, SuccessActivity::class.java)
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

    override fun toVideoList(userId: String) {
        val intent = Intent(activity, VideoListActivity::class.java)
        intent.putExtra("userId",userId)
        activity.startActivity(intent)
        activity.finishAffinity()
        activity.backwardTransition()
    }
}