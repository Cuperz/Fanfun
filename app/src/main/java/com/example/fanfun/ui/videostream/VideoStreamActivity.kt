package com.example.fanfun.ui.videostream


import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.VideoView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.fanfun.R
import com.example.fanfun.utils.App
import com.example.fanfun.utils.backwardTransition
import com.example.fanfun.utils.bind
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView

class VideoStreamActivity: App(), VideoStreamContract.View {

    private val mVideoView: VideoView by bind(R.id.stream_videopreview)
    private val mVideoStart: MaterialButton by bind(R.id.stream_video_play)
    private val mRequestName: TextView by bind(R.id.stream_username)
    private val mVideoCard: MaterialCardView by bind(R.id.stream_video_card)
    private val mVideoLayout: ConstraintLayout by bind(R.id.stream_layout)
    private val mBackArrow: MaterialButton by bind(R.id.stream_back_arrow)
    private var mVideoPlaying = false
    private var playbackPositionn = 0
    private var mVideoFile: String? = null
    private var mPresenter: VideoStreamContract.Presenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_stream)

        mPresenter = VideoStreamPresenter(this)
        mVideoStart.setOnClickListener { playVideo() }
        mBackArrow.setOnClickListener { onBackPressed() }
        mVideoCard.setOnClickListener { pauseVideo() }
        mVideoView.setOnCompletionListener { onFinish() }
        setVideo()
    }

    private fun setVideo() {
        mVideoFile = intent.getStringExtra("path")
        if (mVideoFile !== null)  {
            mVideoView.setVideoURI(Uri.parse(mVideoFile))
            playVideo()
        }
    }

    private fun onFinish() {
        mVideoPlaying = false
        mVideoLayout.visibility = View.VISIBLE
        mVideoView.seekTo(1)
    }

    private fun pauseVideo(){
        if (mVideoPlaying) {
            mVideoView.pause()
            mVideoPlaying = false
            mVideoLayout.visibility = View.VISIBLE
            playbackPositionn = mVideoView.currentPosition
        }
    }

    private fun playVideo() {
        mVideoView.seekTo(playbackPositionn)
        mVideoLayout.visibility = View.GONE
        mVideoView.start()
        mVideoPlaying = true
    }

    override fun onBackPressed() {
        super.onBackPressed()
        backwardTransition()
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


}