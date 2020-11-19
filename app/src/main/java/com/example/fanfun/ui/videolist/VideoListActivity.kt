package com.example.fanfun.ui.videolist

import android.os.Bundle
import com.example.fanfun.R
import com.example.fanfun.utils.App

class VideoListActivity: App(), VideoListContract.View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_list)
    }
}