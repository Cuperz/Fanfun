package com.example.fanfun.ui.pending

import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.fanfun.R
import com.example.fanfun.adapter.PendingAdapter
import com.example.fanfun.model.Request

class PendingFragment: Fragment(), PendingContract.View {

    private lateinit var mRecycler: RecyclerView
    private lateinit var mRefresh: SwipeRefreshLayout
    private lateinit var mAdapter: PendingAdapter
    private lateinit var emptyText :TextView
    var mPresenter: PendingContract.Presenter? = null

    fun newInstance(): PendingFragment {
        return PendingFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_pending, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mPresenter = PendingPresenter(this)
        mRecycler = view.findViewById(R.id.pending_recycler)
        mRefresh = view.findViewById(R.id.refresh_pending)
        emptyText = view.findViewById(R.id.pending_empty_text)
        mRecycler.layoutManager = LinearLayoutManager(this.activity)
        initListener()
    }

    private fun initListener(){
        val pendingList: ArrayList<Request> = mPresenter!!.getPendingList()
        val testList: ArrayList<Request> = ArrayList()
        testList.add(Request("Saludo","Juanma",1, "abc"))
        testList.add(Request("Graduación","Danyela",2, "123"))
        testList.add(Request("Ascenso","Alvaro",3,"zxc"))
        testList.add(Request("Bono","Alejandro",4,"789"))
        mAdapter = PendingAdapter(this, testList)
        mRecycler.adapter = mAdapter

        if (testList.isEmpty()){ emptyText.visibility = View.VISIBLE }

        mRefresh.setOnRefreshListener {
            //mAdapter = PendingAdapter(this, mPresenter!!.getPendingList())
            mAdapter = PendingAdapter(this, testList)
            mRecycler.adapter = mAdapter
            mAdapter.notifyDataSetChanged()
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

    fun toRecord(userId: String) {
        mPresenter?.toRecord(userId)
    }
}