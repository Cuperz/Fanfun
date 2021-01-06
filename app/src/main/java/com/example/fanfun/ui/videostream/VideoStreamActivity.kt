package com.example.fanfun.ui.videostream



import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.example.fanfun.R
import com.example.fanfun.model.Request
import com.example.fanfun.utils.*
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView

class VideoStreamActivity: App(), VideoStreamContract.View, Player.EventListener{

    private val mVideoStart: MaterialButton by bind(R.id.stream_video_play)
    private val mRequestName: TextView by bind(R.id.stream_username)
    private val mVideoCard: MaterialCardView by bind(R.id.stream_video_card)
    private val mBackArrow: MaterialButton by bind(R.id.stream_back_arrow)
    private val mProfileImage: ImageView by bind(R.id.stream_image)
    private var mVideoPlaying = false
    private var mPresenter: VideoStreamContract.Presenter? = null
    private var mRequest: Request? = null

    private val playerView: PlayerView by bind(R.id.exoplayerView)
    private var playWhenReady = true
    private var currentWindow = 0
    private var playbackPosition: Long? = 0
    private val progressBar: ProgressBar by bind(R.id.stream_progress_bar)
    private var mPlayer: SimpleExoPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_stream)

        mPresenter = VideoStreamPresenter(this)
        mRequest = intent.getStringExtra("request")!!.toRequest()
        mRequestName.text = fullName(mRequest?.user!!.name,mRequest?.user!!.lastname)
        loadImage(this, mRequest?.user!!.picture, mProfileImage)

        mVideoStart.setOnClickListener { playVideo() }
        mBackArrow.setOnClickListener { onBackPressed() }
        mVideoCard.setOnClickListener { pauseVideo() }

    }

    private fun initPlayer() {
        val mediaItem: MediaItem = MediaItem.fromUri(Uri.parse(mRequest?.url))
        mPlayer = SimpleExoPlayer.Builder(this).build()
        mPlayer?.setMediaItem(mediaItem)
        playerView.player = mPlayer
        mPlayer?.playWhenReady = playWhenReady;
        mPlayer?.seekTo(currentWindow, playbackPosition ?: 0)
        mPlayer?.addListener(this)
        mPlayer?.prepare()
    }

    private fun releasePlayer() {
        if (mPlayer != null){
            playWhenReady = mPlayer?.playWhenReady!!
            playbackPosition = mPlayer?.currentPosition ?: 0
            currentWindow = mPlayer?.currentWindowIndex!!
            mPlayer?.removeListener(this)
            mPlayer?.release()
            mPlayer = null
        }
    }

    override fun onPlaybackStateChanged(state: Int) {
        super.onPlaybackStateChanged(state)
        when (state) {
            ExoPlayer.STATE_IDLE -> {}
            ExoPlayer.STATE_BUFFERING -> {
                if (!mVideoPlaying) progressBar.visibility = View.VISIBLE
                mVideoStart.visibility = View.GONE
            }
            ExoPlayer.STATE_READY -> {
                progressBar.visibility = View.GONE
                mVideoPlaying = true
            }
            ExoPlayer.STATE_ENDED -> onFinish()
            else -> {}
        }
    }

    private fun onFinish() {
        mVideoPlaying = false
        mVideoStart.visibility = View.VISIBLE
    }

    private fun pauseVideo(){
        if (mVideoPlaying) {
            mPlayer?.pause()
            playbackPosition = mPlayer?.currentPosition
            mVideoPlaying = false
            mVideoStart.visibility = View.VISIBLE
        }
    }

    private fun playVideo() {
        if (mPlayer?.playbackState == ExoPlayer.STATE_ENDED) mPlayer?.seekTo(0)
            mPlayer?.play()
            mVideoStart.visibility = View.GONE
            mVideoPlaying = true
    }

    override fun onBackPressed() {
        super.onBackPressed()
        releasePlayer()
        backwardTransition()
    }

    override fun onStart() {
        initPlayer()
        super.onStart()
    }

    override fun onPause() {
        super.onPause()
        pauseVideo()
        mVideoStart.visibility = View.VISIBLE
    }

    override fun onStop() {
        super.onStop()
        pauseVideo()
        mVideoStart.visibility = View.VISIBLE
    }

    override fun onDestroy() {
        releasePlayer()
        super.onDestroy()
    }
}