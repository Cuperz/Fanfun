package com.example.fanfun.ui.sent

import android.content.Intent
import com.example.fanfun.ui.success.SuccessActivity

class SentRouter(val mFragment: SentFragment): SentContract.Router {

    override fun playVideo() {
        //Cambiar a video luego
        val intent = Intent(mFragment.activity, SuccessActivity::class.java)
        mFragment.activity?.startActivity(intent)
        mFragment.activity?.finishAffinity()
    }
}