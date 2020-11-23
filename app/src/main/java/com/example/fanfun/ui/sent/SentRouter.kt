package com.example.fanfun.ui.sent

import android.content.Intent
import com.example.fanfun.ui.success.SuccessActivity
import com.example.fanfun.ui.videoresult.VideoResultActivity
import com.example.fanfun.utils.FROM_SENT

class SentRouter(val mFragment: SentFragment): SentContract.Router {

    override fun playVideo() {
        //Cambiar a video luego
        /*val intent = Intent(mFragment.activity, VideoResultActivity::class.java)
        intent.putExtra("from", FROM_SENT)
        mFragment.activity?.startActivity(intent)
        mFragment.activity?.finishAffinity()*/
    }
}