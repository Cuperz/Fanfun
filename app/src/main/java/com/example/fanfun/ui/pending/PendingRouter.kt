package com.example.fanfun.ui.pending

import android.content.Intent
import com.example.fanfun.model.Request
import com.example.fanfun.ui.camera.CameraActivity
import com.example.fanfun.utils.forwardTransition
import com.example.fanfun.utils.toJson

class PendingRouter(val fragment: PendingFragment): PendingContract.Router {

    override fun toRecord(request: Request) {
        val intent = Intent(fragment.activity, CameraActivity::class.java)
        intent.putExtra("request", request.toJson())
        fragment.activity?.startActivity(intent)
        fragment.activity?.finishAffinity()
        fragment.activity?.forwardTransition()
    }
}