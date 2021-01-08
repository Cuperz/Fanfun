package com.example.fanfun.ui.videoupload

import android.content.Intent
import com.example.fanfun.model.Request
import com.example.fanfun.ui.success.SuccessActivity
import com.example.fanfun.utils.FROM_ERROR
import com.example.fanfun.utils.FROM_SUCCESS
import com.example.fanfun.utils.forwardTransition
import com.example.fanfun.utils.toJson

class VideoUploadRouter(val activity: VideoUploadActivity): VideoUploadContract.Router {

    override fun toError() {
        val intent = Intent(activity, SuccessActivity::class.java)
        intent.putExtra("result", FROM_ERROR)
        activity.startActivity(intent)
        activity.finishAffinity()
        activity.forwardTransition()
    }

    override fun toSuccess() {
        val intent = Intent(activity, SuccessActivity::class.java)
        intent.putExtra("result", FROM_SUCCESS)
        activity.startActivity(intent)
        activity.finishAffinity()
        activity.forwardTransition()
    }
}