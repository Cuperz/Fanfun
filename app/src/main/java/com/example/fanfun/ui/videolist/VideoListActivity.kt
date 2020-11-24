package com.example.fanfun.ui.videolist

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.fanfun.R
import com.example.fanfun.adapter.VideoListAdapter
import com.example.fanfun.model.Model
import com.example.fanfun.utils.App
import com.example.fanfun.utils.bind
import com.google.android.material.button.MaterialButton

class VideoListActivity: App(), VideoListContract.View {

    private lateinit var mRecycler: RecyclerView
    private lateinit var mAdapter: VideoListAdapter
    private val mBackArrow: MaterialButton by bind(R.id.list_back_arrow)
    var mPresenter: VideoListContract.Presenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_list)


        mPresenter = VideoListPresenter(this)
        mRecycler = findViewById(R.id.video_list_recycler)
        mRecycler.layoutManager = LinearLayoutManager(this)
        initListener()

        mBackArrow.setOnClickListener { onBackPressed() }
    }

    private fun initListener() {
        val userVideos: ArrayList<String> = mPresenter!!.getVideos("1234")
        mAdapter = VideoListAdapter(this, userVideos)
        mRecycler.adapter = mAdapter
        mAdapter.notifyDataSetChanged()
    }

    fun toVideo(path: String) {
        mPresenter?.toWatchVideo(path)
    }

    fun deleteVideo(path: String) {
        mPresenter?.deleteVideo("1234",path)
    }

    override fun videoDeleted(userVideos: ArrayList<String>?) {
        mAdapter.videoDeleted(userVideos)
    }


}