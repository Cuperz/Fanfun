package com.example.fanfun.ui.videoresult

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import android.widget.VideoView
import androidx.appcompat.widget.AppCompatImageButton
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.fanfun.R
import com.example.fanfun.utils.App
import com.example.fanfun.utils.bind
import com.google.android.material.button.MaterialButton
import java.io.File

class VideoResultActivity: App(), VideoResultContract.View {

    private var mPresenter: VideoResultContract.Presenter? = null
    private val mSendButton: MaterialButton by bind(R.id.video_result_button)
    private val mVideoView: VideoView by bind(R.id.video_preview)
    private val mVideoStart: MaterialButton by bind(R.id.result_video_play)
    private val mVideoDelete: AppCompatImageButton by bind(R.id.result_delete_button)
    private val mVideoSave: TextView by bind(R.id.result_save_button)
    private var mVideoFile: String? = null

    private val mVideoLayout: ConstraintLayout by bind(R.id.video_buttons_layout)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_result)
        mPresenter = VideoResultPresenter(this)
        mVideoStart.setOnClickListener { showVideo() }
        mVideoDelete.setOnClickListener { deleteVideo() }
        mVideoSave.setOnClickListener { saveVideo() }
        mSendButton.setOnClickListener { sendVideo() }


        mVideoFile = intent.getStringExtra("path")
        if (mVideoFile !== null)  showPreview()

        mVideoView.setOnCompletionListener {
            onFinish()
        }

    }

    private fun saveVideo() {
        Toast.makeText(this, "Video Guardado", Toast.LENGTH_LONG).show()
    }

    private fun deleteVideo() {
        val file = File(mVideoFile!!).delete()
        mPresenter?.toCamera()
        Toast.makeText(this, "Video Borrado", Toast.LENGTH_LONG).show()
    }

    private fun onFinish() {
        mVideoLayout.visibility = View.VISIBLE
        mVideoView.seekTo(1)
    }

    private fun sendVideo() {
        Toast.makeText(this, "Video Enviado", Toast.LENGTH_LONG).show()
        mPresenter?.sendVideo(mVideoFile)
    }

    private fun showPreview() {
        mVideoView.setVideoURI(Uri.parse(mVideoFile))
        mVideoView.seekTo(1)
    }

    private fun showVideo(){
        mVideoLayout.visibility = View.GONE
        mVideoView.start()
    }

}