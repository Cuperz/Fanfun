package com.example.fanfun.ui.sent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.fanfun.R
import com.example.fanfun.adapter.PendingAdapter
import com.example.fanfun.adapter.SentAdapter
import com.example.fanfun.adapter.SketchAdapter
import com.example.fanfun.model.Model

class SentFragment: Fragment(), SentContract.View {

    private lateinit var mRecycler: RecyclerView
    private lateinit var mRefresh: SwipeRefreshLayout
    private lateinit var mAdapter: SentAdapter
    private var mPresenter: SentContract.Presenter? = null
    private lateinit var emptyText : TextView

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
        emptyText = view.findViewById(R.id.sent_empty_text)
        mRecycler.layoutManager = LinearLayoutManager(this.activity)
        initListener()
    }

    private fun initListener() {

        val testList: ArrayList<Model.Sent> = ArrayList()
        testList.add(Model.Sent("asd","asdads","asdad"))
        testList.add(Model.Sent("asd","asdads","asdad"))
        testList.add(Model.Sent("asd","asdads","asdad"))
        mAdapter = SentAdapter(this, testList)
        mRecycler.adapter = mAdapter

        if (testList.isEmpty()){ emptyText.visibility = View.VISIBLE }

        mRefresh.setOnRefreshListener {
            mAdapter = SentAdapter(this, testList)
            mRecycler.adapter = mAdapter
            mAdapter.notifyDataSetChanged()
            mRefresh.isRefreshing = false
        }
    }

    fun playVideo() {
        mPresenter?.playVideo()
    }

}