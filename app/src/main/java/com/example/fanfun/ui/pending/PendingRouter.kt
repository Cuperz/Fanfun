package com.example.fanfun.ui.pending

import android.content.Intent
import com.example.fanfun.ui.camera.CameraActivity

class PendingRouter(val fragment: PendingFragment): PendingContract.Router {

    override fun toRecord() {
        val intent = Intent(fragment.activity, CameraActivity::class.java)
        //val intent = Intent(fragment.activity, CameraX::class.java)
        fragment.activity?.startActivity(intent)
    }
}