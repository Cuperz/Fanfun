package com.example.fanfun.ui.home

import com.example.fanfun.network.ProfileResponse
import com.example.fanfun.utils.HAWK_USER_PROFILE
import com.orhanobut.hawk.Hawk

class HomeInteractor(var intOut: HomeContract.InteractorOutput): HomeContract.Interactor {

    override fun getPhoto(): String? {
        val profile: ProfileResponse = Hawk.get(HAWK_USER_PROFILE)
        return profile.photo
    }
}