package com.example.fanfun.ui.sent

import android.content.Intent
import com.example.fanfun.ui.videostream.VideoStreamActivity
import com.example.fanfun.utils.FROM_SENT
import com.example.fanfun.utils.forwardTransition

class SentRouter(private val mFragment: SentFragment): SentContract.Router {

    override fun playVideo() {
        val intent = Intent(mFragment.activity, VideoStreamActivity::class.java)
        intent.putExtra("from", FROM_SENT)
        intent.putExtra("path","https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4")
        mFragment.activity?.startActivity(intent)
        mFragment.activity?.forwardTransition()
    }
}