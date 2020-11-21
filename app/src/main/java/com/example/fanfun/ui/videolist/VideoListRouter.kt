package com.example.fanfun.ui.videolist

import android.content.Intent
import com.example.fanfun.ui.videoresult.VideoResultActivity

class VideoListRouter(val activity: VideoListActivity): VideoListContract.Router {

    override fun toWatchVideo(path: String) {
        val intent = Intent(activity,VideoResultActivity::class.java)
        intent.putExtra("path",path)
        activity.startActivity(intent)
    }
}