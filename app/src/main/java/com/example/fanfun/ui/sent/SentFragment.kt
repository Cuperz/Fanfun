package com.example.fanfun.ui.sent

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.fanfun.R
import com.example.fanfun.adapter.SentAdapter
import com.example.fanfun.model.Request
import com.example.fanfun.utils.checkPermission

class SentFragment: Fragment(), SentContract.View {

    private lateinit var mRecycler: RecyclerView
    private lateinit var mRefresh: SwipeRefreshLayout
    private lateinit var mAdapter: SentAdapter
    private var mPresenter: SentContract.Presenter? = null
    private lateinit var emptyText : TextView
    private var videoUrl: String? = null
    private var mRequest: Request? = null

    fun newInstance(): SentFragment {
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
        mPresenter?.getList()

        mRefresh.setOnRefreshListener {
            mPresenter?.getList()
        }
    }

    override fun listResult(sentList: java.util.ArrayList<Request>) {
        mAdapter = SentAdapter(this, sentList)
        mRecycler.adapter = mAdapter
        mAdapter.notifyDataSetChanged()
        mRefresh.isRefreshing = false

        if (sentList.isEmpty()){ emptyText.visibility = View.VISIBLE }
    }

    fun playVideo(request: Request) {
        mRequest = request
        checkPermission(this.activity!!, Manifest.permission.INTERNET){
            mPresenter?.playVideo(request)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if ( ContextCompat.checkSelfPermission(this.activity!!, Manifest.permission.INTERNET) == PackageManager.PERMISSION_GRANTED){
            mPresenter?.playVideo(mRequest!!)
        }else{
            playVideo(mRequest!!)
        }
    }


}