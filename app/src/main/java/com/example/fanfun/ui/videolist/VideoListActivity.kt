package com.example.fanfun.ui.videolist

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.fanfun.R
import com.example.fanfun.adapter.VideoListAdapter
import com.example.fanfun.model.Model
import com.example.fanfun.utils.App

class VideoListActivity: App(), VideoListContract.View {

    private lateinit var mRecycler: RecyclerView
    var mPresenter: VideoListContract.Presenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_list)

        mPresenter = VideoListPresenter(this)
        mRecycler = findViewById(R.id.video_list_recycler)
        mRecycler.layoutManager = LinearLayoutManager(this)
        initListener()
    }

    private fun initListener() {
        val testList: ArrayList<Model.ListVideo> = ArrayList()
        testList.add(Model.ListVideo("Ayer","asdasd"))
        testList.add(Model.ListVideo("Hace 2 días","asdasd"))
        testList.add(Model.ListVideo("Hace 3 días","asdasd"))
        mRecycler.adapter = VideoListAdapter(this, testList)
        (mRecycler.adapter as VideoListAdapter).notifyDataSetChanged()
    }


}