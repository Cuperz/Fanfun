package com.example.fanfun.ui.videostream


import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.VideoView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.fanfun.R
import com.example.fanfun.model.Request
import com.example.fanfun.utils.*
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView

class VideoStreamActivity: App(), VideoStreamContract.View {

    private val mVideoView: VideoView by bind(R.id.stream_videopreview)
    private val mVideoStart: MaterialButton by bind(R.id.stream_video_play)
    private val mRequestName: TextView by bind(R.id.stream_username)
    private val mVideoCard: MaterialCardView by bind(R.id.stream_video_card)
    private val mBackArrow: MaterialButton by bind(R.id.stream_back_arrow)
    private val mProgressBar: ProgressBar by bind(R.id.stream_progress_bar)
    private val mProfileImage: ImageView by bind(R.id.stream_profile_button)
    private var mVideoPlaying = false
    private var playbackPositionn = 0
    private var mPresenter: VideoStreamContract.Presenter? = null
    private var mRequest: Request? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_stream)

        mPresenter = VideoStreamPresenter(this)
        mRequest = intent.getStringExtra("request")!!.toRequest()
        mRequestName.text = fullName(mRequest?.user!!.name,mRequest?.user!!.lastname)
        loadImage(this, mRequest?.user!!.picture, mProfileImage)
        setVideo()

        mVideoStart.setOnClickListener { playVideo() }
        mBackArrow.setOnClickListener { onBackPressed() }
        mVideoCard.setOnClickListener { pauseVideo() }
        mVideoView.setOnCompletionListener { onFinish() }
    }

    private fun setVideo() {
        val url = Uri.parse(mRequest?.url)
        if (mRequest?.url !== null)  mVideoView.setVideoURI(url)
        mVideoView.setOnPreparedListener { playVideo() }
    }

    private fun onFinish() {
        mVideoPlaying = false
        mVideoStart.visibility = View.VISIBLE
        mVideoView.seekTo(1)
    }

    private fun pauseVideo(){
        if (mVideoPlaying) {
            mVideoView.pause()
            mVideoPlaying = false
            mVideoStart.visibility = View.VISIBLE
            playbackPositionn = mVideoView.currentPosition
        }
    }

    private fun playVideo() {
        mVideoView.seekTo(playbackPositionn)
        mVideoStart.visibility = View.GONE
        mProgressBar.visibility = View.GONE
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
        mVideoStart.visibility = View.VISIBLE
    }

    override fun onResume() {
        super.onResume()
        mVideoView.seekTo(playbackPositionn)
    }

    override fun onStop() {
        mVideoPlaying = false
        mVideoView.stopPlayback()
        mVideoStart.visibility = View.VISIBLE
        super.onStop()
    }


}