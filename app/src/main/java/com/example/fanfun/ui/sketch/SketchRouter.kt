package com.example.fanfun.ui.sketch

import android.content.Intent
import com.example.fanfun.model.Request
import com.example.fanfun.ui.camera.CameraActivity
import com.example.fanfun.ui.videolist.VideoListActivity
import com.example.fanfun.utils.backwardTransition
import com.example.fanfun.utils.forwardTransition
import com.example.fanfun.utils.toJson

class SketchRouter(val fragment: SketchFragment): SketchContract.Router  {

    override fun toVideoList(request: Request) {
        val intent = Intent(fragment.activity, VideoListActivity::class.java)
        intent.putExtra("request",request.toJson())
        fragment.activity?.startActivity(intent)
        fragment.activity?.forwardTransition()
    }

    override fun toCamera(request: Request) {
        val intent = Intent(fragment.activity, CameraActivity::class.java)
        intent.putExtra("request", request.toJson())
        fragment.activity?.startActivity(intent)
        fragment.activity?.finishAffinity()
        fragment.activity?.forwardTransition()
    }
}