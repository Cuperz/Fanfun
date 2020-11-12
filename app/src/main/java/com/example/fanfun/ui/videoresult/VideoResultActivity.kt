package com.example.fanfun.ui.videoresult

import android.os.Bundle
import com.example.fanfun.R
import com.example.fanfun.utils.App

class VideoResultActivity: App(), VideoResultContract.View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_result)
    }
}