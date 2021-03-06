package com.example.fanfun.ui.sent

import android.content.Intent
import com.example.fanfun.model.Request
import com.example.fanfun.ui.videostream.VideoStreamActivity
import com.example.fanfun.utils.FROM_SENT
import com.example.fanfun.utils.forwardTransition
import com.example.fanfun.utils.toJson

class SentRouter(private val mFragment: SentFragment): SentContract.Router {

    override fun playVideo(request: Request) {
        val intent = Intent(mFragment.activity, VideoStreamActivity::class.java)
        intent.putExtra("from", FROM_SENT)
        intent.putExtra("request",request.toJson())
        mFragment.activity?.startActivity(intent)
        mFragment.activity?.forwardTransition()
    }
}