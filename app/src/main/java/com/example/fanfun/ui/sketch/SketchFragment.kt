package com.example.fanfun.ui.sketch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fanfun.R

class SketchFragment: Fragment(), SketchContract.View {

    fun newInstance(): SketchFragment? {
        return SketchFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_sketch, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var mPresenter: SketchContract.Presenter = SketchPresenter(this)
    }
}