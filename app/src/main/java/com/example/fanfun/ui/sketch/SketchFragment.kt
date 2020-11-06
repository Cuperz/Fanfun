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
import com.example.fanfun.model.Model

class SketchFragment: Fragment(), SketchContract.View {

    private lateinit var mRecycler: RecyclerView
    private lateinit var mRefresh: SwipeRefreshLayout

    fun newInstance(): SketchFragment? {
        return SketchFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_sketch, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var mPresenter: SketchContract.Presenter = SketchPresenter(this)
        mRecycler = view.findViewById(R.id.sketch_recycler)
        mRefresh = view.findViewById(R.id.sketch_refresh)
        mRecycler.layoutManager = LinearLayoutManager(this.activity)
        initListener()
    }

    private fun initListener() {

        val testList: ArrayList<Model.Sketch> = ArrayList()
        testList.add(Model.Sketch("asd","asdads","asdad"))
        testList.add(Model.Sketch("asd","asdads","asdad"))
        mRecycler.adapter = SketchAdapter(this, testList)
        (mRecycler.adapter as SketchAdapter).notifyDataSetChanged()

        mRefresh.setOnRefreshListener {
            mRecycler.adapter = SketchAdapter(this, testList)
            (mRecycler.adapter as SketchAdapter).notifyDataSetChanged()
            mRefresh.isRefreshing = false
        }
    }

    fun showDialog() {
        val commentDialog = LayoutInflater.from(activity).inflate(R.layout.comment_dialog,null)
        val dialogBuilder = AlertDialog.Builder(activity).setView(commentDialog)
        val dialogInstance = dialogBuilder.show()
        dialogInstance.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val closeButton: ImageView = commentDialog.findViewById(R.id.dialog_close_button)
        closeButton.setOnClickListener { dialogInstance.dismiss() }
    }
}