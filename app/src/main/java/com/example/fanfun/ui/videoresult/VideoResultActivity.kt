package com.example.fanfun.ui.videoresult

import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.webkit.URLUtil
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import android.widget.VideoView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.fanfun.R
import com.example.fanfun.utils.*
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView

class VideoResultActivity: App(), VideoResultContract.View {

    private var mPresenter: VideoResultContract.Presenter? = null
    private val mSendButton: MaterialCardView by bind(R.id.video_result_button)
    private val mSendText: TextView by bind(R.id.send_button_text)
    private val mSendProgress: ProgressBar by bind(R.id.send_progress_bar)
    private val mVideoView: VideoView by bind(R.id.video_preview)
    private val mVideoStart: MaterialButton by bind(R.id.result_video_play)
    private val mVideoDelete: TextView by bind(R.id.result_delete_button)
    private val mVideoSave: TextView by bind(R.id.result_save_button)
    private val mVideoCard: MaterialCardView by bind(R.id.result_video_card)
    private var mVideoPlaying = false
    private var playbackPositionn = 1
    private var mVideoFile: String? = null
    private var mVideoFrom: Int? = null
    private var mUserId: String ? = null

    private val mVideoLayout: ConstraintLayout by bind(R.id.video_buttons_layout)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_result)
        mPresenter = VideoResultPresenter(this)
        mVideoStart.setOnClickListener { showVideo() }
        mVideoDelete.setOnClickListener { deleteVideo() }
        mVideoSave.setOnClickListener { saveVideo() }
        mSendButton.setOnClickListener { sendVideo() }
        mVideoCard.setOnClickListener { pauseVideo() }

        mVideoFrom = intent.getIntExtra("from", FROM_CAMERA)
        mVideoFile = intent.getStringExtra("path")

        mUserId = intent.getStringExtra("userId")

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
            mPresenter?.toCamera(mUserId!!) }
    }

    private fun saveInHawk() {
        if(!userExist(mUserId!!)) {
            addUser(User(mUserId!!, "Nicolas", "Cumpleaños", userVideos = arrayListOf(mVideoFile!!)))
        }else{
            addUserVideo(mUserId!!,mVideoFile!!)
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
            mPresenter?.deleteVideo(mUserId!!, mVideoFrom!!, mVideoFile)
            dialogInstance.dismiss()
        }
    }

    private fun onFinish() {
        mVideoLayout.visibility = View.VISIBLE
        playbackPositionn = 1
        mVideoPlaying = false
        mVideoView.seekTo(1)
    }

    private fun sendVideo() {
        mSendProgress.visibility = View.VISIBLE
        mSendText.text = resources.getText(R.string.sending_video)
        mPresenter?.sendVideo(mVideoFile!!)
    }

    private fun showPreview() {
        mVideoView.setVideoURI(Uri.parse(mVideoFile))
        mVideoView.seekTo(playbackPositionn)
    }


    private fun showVideo(){
        mVideoLayout.visibility = View.GONE
        mVideoView.start()
        mVideoPlaying = true
    }

    private fun pauseVideo(){
        if (mVideoPlaying) {
            mVideoView.pause()
            mVideoPlaying = false
            mVideoLayout.visibility = View.VISIBLE
            playbackPositionn = mVideoView.currentPosition
        }
    }

    override fun onBackPressed() {
        if (mVideoFrom == FROM_CAMERA){
            deleteVideo()
        }else{
            super.onBackPressed()
            //intent.putExtra("userId", mUserId)
            backwardTransition()
        }
    }

    override fun onPause() {
        super.onPause()
        mVideoPlaying = false
        playbackPositionn = mVideoView.currentPosition
        mVideoLayout.visibility = View.VISIBLE
    }

    override fun onResume() {
        super.onResume()
        mVideoView.seekTo(playbackPositionn)
    }

    override fun onStop() {
        mVideoPlaying = false
        mVideoView.stopPlayback()
        mVideoLayout.visibility = View.VISIBLE
        super.onStop()
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

    override fun videoFailed() {
        mSendProgress.visibility = View.GONE
        mSendText.text = "Envio Fallido"
    }
}