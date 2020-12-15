package com.example.fanfun.ui.camera

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import com.example.fanfun.R
import com.example.fanfun.utils.App
import com.example.fanfun.utils.bind
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView
import com.otaliastudios.cameraview.CameraListener
import com.otaliastudios.cameraview.CameraView
import com.otaliastudios.cameraview.VideoResult
import com.otaliastudios.cameraview.controls.Facing
import com.otaliastudios.cameraview.controls.Mode
import com.otaliastudios.cameraview.size.AspectRatio
import com.otaliastudios.cameraview.size.SizeSelectors
import java.io.File

class CameraActivity: App(), CameraContract.View {

    private val mCamera: CameraView by bind(R.id.camera_view)
    private val mRecordButton: MaterialButton by bind(R.id.camera_record_button)
    private val mBackButton: MaterialButton by bind(R.id.camera_back_arrow)
    private val mReverseButton: MaterialButton by bind(R.id.camera_view_reverse_button)
    private val mSendButton: MaterialButton by bind(R.id.camera_send_button)
    private val mDialog: MaterialCardView by bind(R.id.camera_dialog)
    private val mDialogClose: ImageView by bind(R.id.camera_dialog_close_button)
    private val mShowDialog: MaterialButton by bind(R.id.camera_show_dialog)
    private var isRecording: Boolean = false
    private var mPresenter: CameraContract.Presenter? = null
    private var mVideoPath: String? = null
    private var mCurrentFile: File? = null
    private var mUserId: String? =null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)
        setScreen()
        setCamera()
        setButton()

        mUserId = intent.getStringExtra("userId")

        mPresenter = CameraPresenter(this)
        mRecordButton.setOnClickListener { if (!isRecording) record() else stopRecord() }
        mReverseButton.setOnClickListener { revertCamera() }
        mBackButton.setOnClickListener { onBackPressed() }
        mDialogClose.setOnClickListener { closeDialog() }
        mSendButton.setOnClickListener { sendVideo() }
        mShowDialog.setOnClickListener { showDialog() }
        mSendButton.isEnabled = false

        mCamera.addCameraListener(object : CameraListener() {

            override fun onVideoTaken(result: VideoResult) {
                mCurrentFile = result.file
                mVideoPath = result.file.absolutePath
                mSendButton.isEnabled = true
            }

            override fun onVideoRecordingStart() {
                isRecording = true
                mSendButton.isEnabled = false
                mRecordButton.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#ff5276"))
            }

            override fun onVideoRecordingEnd() {
                isRecording = false
                mRecordButton.backgroundTintList = ColorStateList.valueOf(Color.WHITE)
            }

        })
    }

    private fun showDialog() {
        mDialog.visibility = View.VISIBLE
        mShowDialog.visibility = View.GONE
    }

    private fun closeDialog() {
        mDialog.visibility = View.GONE
        mShowDialog.visibility = View.VISIBLE
    }

    private fun revertCamera() {
        if (!isRecording) {
            if (mCamera.facing == Facing.FRONT) {
                mCamera.facing = Facing.BACK
            } else {
                mCamera.facing = Facing.FRONT
            }
        }
    }

    private fun stopRecord() {
        mCamera.stopVideo()
    }

    private fun record() {
        mCurrentFile?.delete()
        val videoFile = File.createTempFile("video_test_", ".mp4", getOutputDirectory())
        mCamera.takeVideo(videoFile)
    }

    private fun sendVideo() {
        if(!isRecording) {
            mPresenter?.sendVideo(mUserId!!, mVideoPath)
        }
    }


    private fun getOutputDirectory(): File? {
        val mediaDir = externalMediaDirs.firstOrNull()?.let {
            File(it, resources.getString(R.string.app_name)).apply { mkdirs() } }
        return if (mediaDir != null && mediaDir.exists())
            mediaDir else filesDir
    }

    private fun setCamera(){
        mCamera.setLifecycleOwner(this)
        mCamera.facing = Facing.FRONT
        mCamera.mode = Mode.VIDEO
        mCamera.videoBitRate = 2000000
        mCamera.videoMaxDuration = 120000

        val width = SizeSelectors.minWidth(240)
        val height = SizeSelectors.minHeight(426)
        val dimensions = SizeSelectors.and(width, height)
        val ratio = SizeSelectors.aspectRatio(AspectRatio.of(9, 16), 0f)


        val result = SizeSelectors.or(
                SizeSelectors.and(ratio, dimensions),  // Try to match both constraints
                ratio,  // If none is found, at least try to match the aspect ratio
                SizeSelectors.smallest() // If none is found, take the biggest
        )
        mCamera.setVideoSize(result)
    }

    private fun setScreen(){
        window.apply {
            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            //setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
            statusBarColor = Color.TRANSPARENT
        }
    }

    private fun setButton(){
        mRecordButton.backgroundTintList = ColorStateList.valueOf(Color.WHITE)
        val states = arrayOf(
                intArrayOf(android.R.attr.state_enabled),
                intArrayOf(-android.R.attr.state_enabled)
        )
        val colors = intArrayOf(
                Color.parseColor("#00e096"), // enabled color
                Color.parseColor("#8000e096") // disabled color
        )
        val iconColors = intArrayOf(
                Color.parseColor("#ffffff"),
                Color.parseColor("#80ffffff")
        )
        val iconStates = ColorStateList(states, iconColors)
        val colorStates = ColorStateList(states, colors)
        mSendButton.backgroundTintList = colorStates
        mSendButton.iconTint = iconStates
    }

    override fun onBackPressed() {
        if (!isRecording){
            mCurrentFile?.delete()
            mPresenter?.toHome()
        }
    }

    override fun onResume() {
        super.onResume()
        isRecording = false
        mCamera.open()
    }

    override fun onPause() {
        super.onPause()
        isRecording = false
        mCamera.close()
    }

    override fun onDestroy() {
        super.onDestroy()
        isRecording = false
        mCamera.destroy()
    }
}