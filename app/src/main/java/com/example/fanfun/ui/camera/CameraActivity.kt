package com.example.fanfun.ui.camera

import android.Manifest
import android.content.pm.ActivityInfo
import android.content.pm.PackageManager
import android.hardware.Camera.CameraInfo
import android.media.CamcorderProfile
import android.media.MediaRecorder
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.view.WindowInsets
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.fanfun.R
import com.example.fanfun.utils.bind
import com.google.android.material.button.MaterialButton
import java.io.File
import java.io.IOException


@Suppress("DEPRECATION")
class CameraActivity:AppCompatActivity(), CameraContract.View, SurfaceHolder.Callback2 {

    var mRecorder: MediaRecorder? = null
    var mHolder: SurfaceHolder? = null
    var mRecording: Boolean = false
    var mCameraView: SurfaceView? = null
    var mPresenter: CameraContract.Presenter? = null
    var file: File? = null
    val recordButton: MaterialButton by bind(R.id.camera_record_button)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPresenter = CameraPresenter(this)

        setScreen()
        mRecorder = MediaRecorder()
        checkMediaPermission()
        //initRecorder()
        setContentView(R.layout.activity_camera)

        mCameraView = findViewById(R.id.camera_view)
        mHolder = mCameraView?.holder
        mHolder?.addCallback(this)
        mHolder?.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS)
        recordButton.setOnClickListener { onRecordPressed() }
    }

    private fun checkMediaPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.RECORD_AUDIO), 10);
        } else {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), 9);
            } else {
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 8);
                } else {
                    initRecorder()
                }
            }
        }
    }

    private fun onRecordPressed() {
        if (mRecording) {
            mRecorder?.stop()
            mRecording = false

            initRecorder()
            prepareRecorder()
        } else {
            mRecording = true
            mRecorder?.start()
        }
    }

    private fun initRecorder() {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.RECORD_AUDIO), 10);
        } else {


            mRecorder?.setAudioSource(MediaRecorder.AudioSource.DEFAULT)
            mRecorder?.setVideoSource(MediaRecorder.VideoSource.DEFAULT)
            val cpHigh: CamcorderProfile = CamcorderProfile.get(CamcorderProfile.QUALITY_720P)
            cpHigh.fileFormat = MediaRecorder.OutputFormat.MPEG_4
            mRecorder?.setProfile(cpHigh)

            val path = Environment.DIRECTORY_DCIM
            Log.d("Camera", path)
            val dir = File(path)
            if (!dir.exists()) {
                dir.mkdirs()
                Log.d("Camera", dir.absolutePath)
            }
            val myfile = "$path/filename.mp4"
            Log.d("Camera", myfile)


            //mRecorder?.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
            mRecorder?.setOutputFile(myfile)
            mRecorder?.setMaxDuration(10000)
            mRecorder?.setMaxFileSize(10000000)
        }
    }


    private fun prepareRecorder() {
        mRecorder?.setPreviewDisplay(mHolder?.surface)
        try {
            mRecorder?.prepare()
        } catch (e: IllegalStateException) {
            e.printStackTrace()
            finish()
        } catch (e: IOException) {
            e.printStackTrace()
            finish()
        }
    }



    override fun surfaceCreated(holder: SurfaceHolder) {
        prepareRecorder()
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {
        if (mRecording) {
            mRecorder?.stop()
            mRecording = false
        }
        mRecorder?.release()
        finish()
    }

    override fun surfaceRedrawNeeded(holder: SurfaceHolder) {
    }

    private fun setScreen(){
        //requestWindowFeature(Window.FEATURE_NO_TITLE)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        }
    }
}