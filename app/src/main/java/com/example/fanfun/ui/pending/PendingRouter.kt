package com.example.fanfun.ui.pending

import android.content.Intent
import com.example.fanfun.ui.camera.CameraActivity
import com.example.fanfun.utils.forwardTransition

class PendingRouter(val fragment: PendingFragment): PendingContract.Router {

    override fun toRecord() {
        val intent = Intent(fragment.activity, CameraActivity::class.java)
        fragment.activity?.startActivity(intent)
        fragment.activity?.finishAffinity()
        fragment.activity?.forwardTransition()
    }
}