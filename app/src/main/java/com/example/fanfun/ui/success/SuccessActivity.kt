package com.example.fanfun.ui.success

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fanfun.R
import com.example.fanfun.utils.App
import com.example.fanfun.utils.bind
import com.google.android.material.button.MaterialButton

class SuccessActivity: App(), SuccessContract.View  {

    var mPresenter: SuccessContract.Presenter? = null
    val okButton: MaterialButton by bind(R.id.succes_button)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_success)

        mPresenter = SuccessPresenter(this)
        okButton.setOnClickListener { mPresenter?.toHome() }
    }
}