package com.example.fanfun.ui.sent

import android.content.Intent
import com.example.fanfun.ui.videostream.VideoStreamActivity
import com.example.fanfun.utils.FROM_SENT
import com.example.fanfun.utils.forwardTransition

class SentRouter(private val mFragment: SentFragment): SentContract.Router {

    override fun playVideo(url: String) {
        val intent = Intent(mFragment.activity, VideoStreamActivity::class.java)
        intent.putExtra("from", FROM_SENT)
        intent.putExtra("path",url)
        mFragment.activity?.startActivity(intent)
        mFragment.activity?.forwardTransition()
    }
}