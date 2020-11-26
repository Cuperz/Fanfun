package com.example.fanfun.ui.videoresult

import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.widget.Toast
import android.widget.VideoView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.fanfun.R
import com.example.fanfun.utils.*
import com.google.android.material.button.MaterialButton
import java.io.File

class VideoResultActivity: App(), VideoResultContract.View {

    private var mPresenter: VideoResultContract.Presenter? = null
    private val mSendButton: MaterialButton by bind(R.id.video_result_button)
    private val mVideoView: VideoView by bind(R.id.video_preview)
    private val mVideoStart: MaterialButton by bind(R.id.result_video_play)
    private val mVideoDelete: TextView by bind(R.id.result_delete_button)
    private val mVideoSave: TextView by bind(R.id.result_save_button)
    private var mVideoFile: String? = null
    private var mVideoFrom: Int? = null

    private val mVideoLayout: ConstraintLayout by bind(R.id.video_buttons_layout)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_result)
        mPresenter = VideoResultPresenter(this)
        mVideoStart.setOnClickListener { showVideo() }
        mVideoDelete.setOnClickListener { deleteVideo() }
        mVideoSave.setOnClickListener { saveVideo() }
        mSendButton.setOnClickListener { sendVideo() }

        mVideoFrom = intent.getIntExtra("from", FROM_CAMERA)
        mVideoFile = intent.getStringExtra("path")
        if (mVideoFile !== null)  showPreview()
        setButtons()

        mVideoView.setOnCompletionListener {
            onFinish()
        }

    }

    private fun saveVideo() {
        val saveDialog = LayoutInflater.from(this).inflate(R.layout.dialog_save,null)
        val dialogBuilder = AlertDialog.Builder(this).setView(saveDialog)
        val dialogInstance = dialogBuilder.show()
        dialogInstance.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val cancelButton: MaterialButton = saveDialog.findViewById(R.id.save_dialog_cancel_button)
        cancelButton.setOnClickListener { dialogInstance.dismiss() }

        val saveButton: MaterialButton = saveDialog.findViewById(R.id.save_dialog_confirm_button)
        saveButton.setOnClickListener {
            saveInHawk()
            dialogInstance.dismiss()
            mPresenter?.toCamera() }
    }

    private fun saveInHawk() {
        if(!userExist("1234")) {
            addUser(User("1234", "Nicolas", "Cumpleaños", userVideos = arrayListOf(mVideoFile!!)))
        }else{
            addUserVideo("1234",mVideoFile!!)
        }
    }

    private fun deleteVideo() {

        val deleteDialog = LayoutInflater.from(this).inflate(R.layout.dialog_delete,null)
        val dialogBuilder = AlertDialog.Builder(this).setView(deleteDialog)
        val dialogInstance = dialogBuilder.show()
        dialogInstance.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val cancelButton: MaterialButton = deleteDialog.findViewById(R.id.delete_dialog_cancel_button)
        cancelButton.setOnClickListener { dialogInstance.dismiss() }

        val deleteButton: MaterialButton = deleteDialog.findViewById(R.id.delete_dialog_confirm_button)
        deleteButton.setOnClickListener {
            mPresenter?.deleteVideo("1234", mVideoFrom!!, mVideoFile)
            dialogInstance.dismiss()
        }
    }

    private fun onFinish() {
        mVideoLayout.visibility = View.VISIBLE
        mVideoView.seekTo(1)
    }

    private fun sendVideo() {
        Toast.makeText(this, "Video Enviado", Toast.LENGTH_LONG).show()
        mPresenter?.sendVideo(mVideoFile!!)
    }

    private fun showPreview() {
        mVideoView.setVideoURI(Uri.parse(mVideoFile))
        mVideoView.seekTo(1)
    }

    private fun showVideo(){
        mVideoLayout.visibility = View.GONE
        mVideoView.start()
    }

    override fun onBackPressed() {
        if (mVideoFrom == FROM_CAMERA){
            deleteVideo()
        }else{
            super.onBackPressed()
            backwardTransition()
        }
    }

    private fun setButtons(){
        when (mVideoFrom) {
            FROM_SKETCH -> {
                mVideoSave.visibility = View.GONE
            }
            FROM_SENT -> {
                mVideoDelete.visibility = View.GONE
                mVideoSave.visibility = View.GONE
            }
            else -> {
                return
            }
        }
    }

}