package com.example.fanfun.ui.videolist

import android.content.Intent
import com.example.fanfun.ui.home.HomeActivity
import com.example.fanfun.ui.videoresult.VideoResultActivity
import com.example.fanfun.utils.FROM_SKETCH
import com.example.fanfun.utils.backwardTransition
import com.example.fanfun.utils.forwardTransition

class VideoListRouter(val activity: VideoListActivity): VideoListContract.Router {

    override fun toWatchVideo(path: String) {
        val intent = Intent(activity,VideoResultActivity::class.java)
        intent.putExtra("path",path)
        intent.putExtra("from", FROM_SKETCH)
        activity.startActivity(intent)
        activity.forwardTransition()
    }

    override fun toHome() {
        val intent = Intent(activity,HomeActivity::class.java)
        activity.startActivity(intent)
        activity.finishAffinity()
        activity.backwardTransition()
    }
}