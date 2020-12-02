package com.example.fanfun.ui.camera

import android.content.Intent
import com.example.fanfun.ui.home.HomeActivity
import com.example.fanfun.ui.videoresult.VideoResultActivity
import com.example.fanfun.utils.backwardTransition
import com.example.fanfun.utils.forwardTransition

class CameraRouter(val activity: CameraActivity): CameraContract.Router {

    override fun sendVideo(userId: String,mVideoPath: String?) {
        val intent = Intent(activity, VideoResultActivity::class.java)
        intent.putExtra("userId", userId)
        intent.putExtra("path", mVideoPath)
        activity.startActivity(intent)
        activity.forwardTransition()
    }

    override fun toHome() {
        val intent = Intent(activity, HomeActivity::class.java)
        activity.startActivity(intent)
        activity.finishAffinity()
        activity.backwardTransition()
    }
}