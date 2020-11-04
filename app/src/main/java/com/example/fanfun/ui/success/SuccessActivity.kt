package com.example.fanfun.ui.success

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fanfun.R

class SuccessActivity:AppCompatActivity(), SuccessContract.View  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_success)

        var mPresenter: SuccessContract.Presenter = SuccessPresenter(this)
    }
}