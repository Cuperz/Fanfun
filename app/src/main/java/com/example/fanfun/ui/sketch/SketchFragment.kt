package com.example.fanfun.ui.sketch

import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.fanfun.R
import com.example.fanfun.adapter.PendingAdapter
import com.example.fanfun.adapter.SketchAdapter
import com.example.fanfun.adapter.VideoListAdapter
import com.example.fanfun.model.Model
import com.example.fanfun.utils.User

class SketchFragment: Fragment(), SketchContract.View {

    private lateinit var mRecycler: RecyclerView
    private lateinit var mRefresh: SwipeRefreshLayout
    var mPresenter: SketchContract.Presenter? = null
    private lateinit var mAdapter: SketchAdapter

    fun newInstance(): SketchFragment {
        return SketchFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_sketch, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mPresenter = SketchPresenter(this)
        mRecycler = view.findViewById(R.id.sketch_recycler)
        mRefresh = view.findViewById(R.id.sketch_refresh)
        mRecycler.layoutManager = LinearLayoutManager(this.activity)
        initListener()
    }

    private fun initListener() {
        val userList: ArrayList<User> = mPresenter!!.getList()
        mAdapter = SketchAdapter(this, userList)
        mRecycler.adapter = mAdapter

        mRefresh.setOnRefreshListener {
            mAdapter.updateList( mPresenter!!.getList())
            mRefresh.isRefreshing = false
        }
    }

    fun showDialog() {
        val commentDialog = LayoutInflater.from(activity).inflate(R.layout.dialog_comment,null)
        val dialogBuilder = AlertDialog.Builder(activity).setView(commentDialog)
        val dialogInstance = dialogBuilder.show()
        dialogInstance.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val closeButton: ImageView = commentDialog.findViewById(R.id.dialog_close_button)
        closeButton.setOnClickListener { dialogInstance.dismiss() }
    }

    fun toVideoList() {
        mPresenter?.toVideoList()
    }

    override fun onResume() {
        super.onResume()
        mAdapter.updateList(mPresenter!!.getList())
    }
}