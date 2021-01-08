package com.example.fanfun.ui.videoupload


import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.work.WorkInfo
import com.example.fanfun.R
import com.example.fanfun.model.Request
import com.example.fanfun.utils.App
import com.example.fanfun.utils.toRequest

class VideoUploadActivity: App(), VideoUploadContract.View {

    private var mPresenter: VideoUploadContract.Presenter? = null
    private var request: Request? = null
    private var videoFile: String? = null

    private val mainHandler = Handler(Looper.getMainLooper())
    private val checkTask = object : Runnable {
        override fun run() {
            checkUpload()
            mainHandler.postDelayed(this, 2000)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload)

        request = intent.getStringExtra("request")!!.toRequest()
        videoFile = intent.getStringExtra("path")

        mPresenter = VideoUploadPresenter(this)
        uploadVideo(request!!, videoFile)

        mainHandler.post(checkTask)
    }

    private fun uploadVideo(request: Request, videoFile: String?) {
        mPresenter?.videoUpload(request, videoFile)
    }

    private fun checkUpload(){
        when(mPresenter?.checkUpload()) {
            WorkInfo.State.SUCCEEDED -> {
                mPresenter?.videoSucceded(request, videoFile)
            }
            WorkInfo.State.FAILED -> {
                mPresenter?.videoFailed(request, videoFile)
            }
            else -> {}
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mainHandler.removeCallbacks(checkTask)
    }
}