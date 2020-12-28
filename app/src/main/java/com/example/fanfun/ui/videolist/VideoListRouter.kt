package com.example.fanfun.ui.videolist

import android.content.Intent
import com.example.fanfun.model.Request
import com.example.fanfun.ui.home.HomeActivity
import com.example.fanfun.ui.videoresult.VideoResultActivity
import com.example.fanfun.utils.FROM_SKETCH
import com.example.fanfun.utils.backwardTransition
import com.example.fanfun.utils.forwardTransition
import com.example.fanfun.utils.toJson

class VideoListRouter(val activity: VideoListActivity): VideoListContract.Router {

    override fun toWatchVideo(path: String, request: Request) {
        val intent = Intent(activity,VideoResultActivity::class.java)
        intent.putExtra("path",path)
        intent.putExtra("request", request.toJson())
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