package com.example.fanfun.ui.videoresult

import android.content.Intent
import com.example.fanfun.ui.camera.CameraActivity
import com.example.fanfun.ui.success.SuccessActivity

class VideoResultRouter( var activity: VideoResultActivity): VideoResultContract.Router {

    override fun toCamera() {
        val intent = Intent(activity, CameraActivity::class.java)
        activity.startActivity(intent)
        activity.finishAffinity()
    }

    override fun toSuccess() {
        val intent = Intent(activity, SuccessActivity::class.java)
        activity.startActivity(intent)
        activity.finishAffinity()
    }
}