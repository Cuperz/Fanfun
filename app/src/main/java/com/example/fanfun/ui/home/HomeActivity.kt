package com.example.fanfun.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.example.fanfun.R
import com.example.fanfun.adapter.HomeAdapter
import com.example.fanfun.ui.pending.PendingFragment
import com.example.fanfun.ui.sent.SentFragment
import com.example.fanfun.ui.sketch.SketchFragment
import com.example.fanfun.utils.bind
import com.google.android.material.tabs.TabLayout

class HomeActivity: AppCompatActivity(), HomeContract.View {

    private var pendingFragment: Fragment ? = null
    private var sketchFragment: Fragment ? = null
    private var sentFragment: Fragment ? = null

    private val mTabBar: TabLayout by bind(R.id.home_tab)
    private val mPager: ViewPager by bind(R.id.mainPager)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        var mPresenter: HomeContract.Presenter = HomePresenter(this)
        initPager()
    }

    private fun initPager(){
        val mAdapter = HomeAdapter(supportFragmentManager)
        pendingFragment = PendingFragment().newInstance()
        sketchFragment = SketchFragment().newInstance()
        sentFragment = SentFragment().newInstance()
        pendingFragment?.let { mAdapter.addFragment(it) }
        sketchFragment?.let { mAdapter.addFragment(it) }
        sentFragment?.let { mAdapter.addFragment(it) }
        mPager.adapter = mAdapter
        mTabBar.setupWithViewPager(mPager)
        mPager.offscreenPageLimit = 2
    }

}