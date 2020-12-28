package com.example.fanfun.ui.profile


import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.fanfun.R
import com.example.fanfun.utils.App
import com.example.fanfun.utils.backwardTransition
import com.example.fanfun.utils.bind
import com.example.fanfun.utils.loadImage
import com.google.android.material.button.MaterialButton

class ProfileActivity: App(), ProfileContract.View {

    private var mPresenter: ProfileContract.Presenter? = null
    private val mBackButton: MaterialButton by bind(R.id.profile_back_arrow)
    private val mLogoutButton: MaterialButton by bind(R.id.logout_button)
    private val mProfileImage: ImageView by bind(R.id.profile_image)
    private val mProfileName: TextView by bind(R.id.profile_name)
    private val mProfileEmail: TextView by bind(R.id.profile_email)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        mPresenter = ProfilePresenter(this)
        mBackButton.setOnClickListener { onBackPressed() }
        mLogoutButton.setOnClickListener { doLogOut() }
        setValues()
    }

    private fun setValues() {
        mPresenter?.getInfo()
    }

    private fun doLogOut() {
        mPresenter?.doLogOut()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        backwardTransition()
    }

    override fun setData(name: String, email: String, photo: String?) {
        mProfileName.text = name
        mProfileEmail.text = email
        loadImage(this,photo,mProfileImage)
    }
}