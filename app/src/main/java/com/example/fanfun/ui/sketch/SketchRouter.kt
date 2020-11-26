package com.example.fanfun.ui.sketch

import android.content.Intent
import com.example.fanfun.ui.camera.CameraActivity
import com.example.fanfun.ui.videolist.VideoListActivity
import com.example.fanfun.utils.backwardTransition
import com.example.fanfun.utils.forwardTransition

class SketchRouter(val fragment: SketchFragment): SketchContract.Router  {

    override fun toVideoList() {
        val intent = Intent(fragment.activity, VideoListActivity::class.java)
        fragment.activity?.startActivity(intent)
        fragment.activity?.forwardTransition()
    }

    override fun toCamera() {
        val intent = Intent(fragment.activity, CameraActivity::class.java)
        fragment.activity?.startActivity(intent)
        fragment.activity?.finishAffinity()
        fragment.activity?.backwardTransition()
    }
}