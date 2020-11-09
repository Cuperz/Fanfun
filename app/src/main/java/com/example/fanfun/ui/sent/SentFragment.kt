package com.example.fanfun.ui.sent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.fanfun.R
import com.example.fanfun.adapter.SentAdapter
import com.example.fanfun.adapter.SketchAdapter
import com.example.fanfun.model.Model

class SentFragment: Fragment(), SentContract.View {

    private lateinit var mRecycler: RecyclerView
    private lateinit var mRefresh: SwipeRefreshLayout
    private var mPresenter: SentContract.Presenter? = null

    fun newInstance(): SentFragment? {
        return SentFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_sent, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mPresenter= SentPresenter(this)
        mRecycler = view.findViewById(R.id.sent_recycler)
        mRefresh = view.findViewById(R.id.sent_refresh)
        mRecycler.layoutManager = LinearLayoutManager(this.activity)
        initListener()
    }

    private fun initListener() {

        val testList: ArrayList<Model.Sent> = ArrayList()
        testList.add(Model.Sent("asd","asdads","asdad"))
        testList.add(Model.Sent("asd","asdads","asdad"))
        testList.add(Model.Sent("asd","asdads","asdad"))
        mRecycler.adapter = SentAdapter(this, testList)
        (mRecycler.adapter as SentAdapter).notifyDataSetChanged()

        mRefresh.setOnRefreshListener {
            mRecycler.adapter = SentAdapter(this, testList)
            (mRecycler.adapter as SentAdapter).notifyDataSetChanged()
            mRefresh.isRefreshing = false
        }
    }

    fun playVideo() {
        mPresenter?.playVideo()
    }

}