package com.example.fanfun.ui.camera

import android.content.Intent
import com.example.fanfun.ui.videoresult.VideoResultActivity

class CameraRouter(val activity: CameraActivity): CameraContract.Router {

    override fun sendVideo(mVideoPath: String?) {
        val intent = Intent(activity, VideoResultActivity::class.java)
        intent.putExtra("path", mVideoPath)
        activity.startActivity(intent)
    }
}