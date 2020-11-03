package com.example.fanfun.ui.profile


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fanfun.R

class ProfileActivity:AppCompatActivity(), ProfileContract.View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        var mPresenter: ProfileContract.Presenter = ProfilePresenter(this)
    }
}