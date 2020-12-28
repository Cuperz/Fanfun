package com.example.fanfun.ui.success

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fanfun.R
import com.example.fanfun.model.Request
import com.example.fanfun.utils.*
import com.google.android.material.button.MaterialButton

class SuccessActivity: App(), SuccessContract.View  {

    private var mPresenter: SuccessContract.Presenter? = null
    private val okButton: MaterialButton by bind(R.id.succes_button)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_success)
        mPresenter = SuccessPresenter(this)

        val request: Request = intent.getStringExtra("request")!!.toRequest()
        val videoFile = intent.getStringExtra("path")
        deleteVideos(request,videoFile!!)

        okButton.setOnClickListener { mPresenter?.toHome() }
    }



    private fun deleteVideos(request: Request, videoFile: String){
        mPresenter?.deleteVideos(request, videoFile)
    }
}