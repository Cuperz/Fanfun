package com.example.fanfun.ui.profile


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fanfun.R
import com.example.fanfun.utils.App
import com.example.fanfun.utils.bind
import com.google.android.material.button.MaterialButton

class ProfileActivity: App(), ProfileContract.View {

    var mPresenter: ProfileContract.Presenter? = null
    val mBackButton: MaterialButton by bind(R.id.profile_back_arrow)
    val mLogoutButton: MaterialButton by bind(R.id.logout_button)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        mPresenter = ProfilePresenter(this)
        mBackButton.setOnClickListener { onBackPressed() }
        mLogoutButton.setOnClickListener { doLogOut() }
    }

    private fun doLogOut() {
        mPresenter?.doLogOut()
    }
}