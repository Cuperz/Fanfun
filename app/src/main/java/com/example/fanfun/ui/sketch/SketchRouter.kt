package com.example.fanfun.ui.sketch

import android.content.Intent
import com.example.fanfun.ui.videolist.VideoListActivity

class SketchRouter(val fragment: SketchFragment): SketchContract.Router  {

    override fun toVideoList() {
        val intent = Intent(fragment.activity, VideoListActivity::class.java)
        fragment.activity?.startActivity(intent)
    }
}