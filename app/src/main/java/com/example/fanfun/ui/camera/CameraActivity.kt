package com.example.fanfun.ui.camera

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.fanfun.R
import com.example.fanfun.utils.App
import com.example.fanfun.utils.bind
import com.google.android.material.button.MaterialButton
import com.otaliastudios.cameraview.CameraListener
import com.otaliastudios.cameraview.CameraView
import com.otaliastudios.cameraview.VideoResult
import com.otaliastudios.cameraview.controls.Facing
import com.otaliastudios.cameraview.controls.Hdr
import com.otaliastudios.cameraview.controls.Mode
import java.io.File

class CameraActivity: App(), CameraContract.View {

    private val mCamera: CameraView by bind(R.id.camera_view)
    private val mRecordButton: MaterialButton by bind(R.id.camera_record_button)
    private val mBackButton: MaterialButton by bind(R.id.camera_back_arrow)
    private val mReverseButton: MaterialButton by bind(R.id.camera_view_reverse_button)
    private val mSendButton: MaterialButton by bind(R.id.camera_send_button)
    private var isRecording: Boolean = false
    private var mPresenter: CameraContract.Presenter? = null
    private var mVideoPath: String? = null
    private var mCurrentFile: File? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)
        setScreen()
        setCamera()

        mPresenter = CameraPresenter(this)
        mRecordButton.setOnClickListener { if (!isRecording) record() else stopRecord() }
        mReverseButton.setOnClickListener { revertCamera() }
        mBackButton.setOnClickListener { onBackPressed() }
        mSendButton.setOnClickListener { sendVideo() }

        mCamera.addCameraListener(object : CameraListener() {

            override fun onVideoTaken(result: VideoResult) {
                mCurrentFile = result.file
                mVideoPath = result.file.absolutePath
            }

            override fun onVideoRecordingStart() {
            }

            override fun onVideoRecordingEnd() {
            }

        })
    }

    private fun revertCamera() {
        if (mCamera.facing == Facing.FRONT){
            mCamera.facing = Facing.BACK
        }else{
            mCamera.facing = Facing.FRONT
        }
    }

    private fun stopRecord() {
        isRecording = false
        mCamera.stopVideo()
    }

    private fun record() {
        isRecording = true
        mCurrentFile?.delete()
        val videoFile = File.createTempFile("video", ".mp4", getOutputDirectory())
        mCamera.takeVideo(videoFile)
    }

    private fun sendVideo() {
        mPresenter?.sendVideo(mVideoPath)
    }


    private fun getOutputDirectory(): File {
        val mediaDir = externalMediaDirs.firstOrNull()?.let {
            File(it, resources.getString(R.string.app_name)).apply { mkdirs() } }
        return if (mediaDir != null && mediaDir.exists())
            mediaDir else filesDir
    }

    private fun setCamera(){
        mCamera.setLifecycleOwner(this)
        mCamera.facing = Facing.FRONT
        mCamera.mode = Mode.VIDEO
        mCamera.hdr = Hdr.ON
        mCamera.videoMaxDuration = 100000
        mCamera.videoMaxSize = 100000000
    }

    private fun setScreen(){
        window.apply {
            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            statusBarColor = Color.TRANSPARENT
        }
    }

    override fun onBackPressed() {
        if (!isRecording){
            super.onBackPressed()
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