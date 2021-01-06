package com.example.fanfun.ui.success

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.fanfun.R
import com.example.fanfun.model.Request
import com.example.fanfun.utils.*
import com.google.android.material.button.MaterialButton

class SuccessActivity: App(), SuccessContract.View  {

    private var mPresenter: SuccessContract.Presenter? = null
    private val okButton: MaterialButton by bind(R.id.succes_button)
    private val mResultSucess: ImageView by bind(R.id.result_success)
    private val mResultError: ImageView by bind(R.id.result_error)
    private val mTitle: TextView by bind(R.id.result_title)
    private val mSubtitle: TextView by bind(R.id.result_subtitle)
    private val mBody: TextView by bind(R.id.result_body)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_success)
        mPresenter = SuccessPresenter(this)

        val result = intent.getIntExtra("result", FROM_SUCCESS)

        setFrom(result)


        okButton.setOnClickListener { mPresenter?.toHome() }
    }

    private fun setFrom(result: Int) {
        if (result != FROM_SUCCESS) {
            mResultSucess.visibility = View.INVISIBLE
            mResultError.visibility = View.VISIBLE
            mSubtitle.visibility = View.VISIBLE
            mBody.visibility = View.VISIBLE
            mTitle.text = resources.getText(R.string.error_message_title)
        }
    }
}