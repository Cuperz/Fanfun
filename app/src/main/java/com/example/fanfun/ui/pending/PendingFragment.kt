package com.example.fanfun.ui.pending

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fanfun.R

class PendingFragment: Fragment(), PendingContract.View {

    fun newInstance(): PendingFragment? {
        return PendingFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_pending, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var mPresenter: PendingContract.Presenter = PendingPresenter(this)
    }
}