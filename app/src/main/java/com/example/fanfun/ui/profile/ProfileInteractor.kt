package com.example.fanfun.ui.profile

import com.example.fanfun.network.ProfileResponse
import com.example.fanfun.utils.HAWK_USER_PROFILE
import com.example.fanfun.utils.deleteHawkData
import com.orhanobut.hawk.Hawk

class ProfileInteractor(var intOut: ProfileContract.InteractorOutput):ProfileContract.Interactor {

    override fun doLogOut() {
        deleteHawkData()
    }

    override fun getInfo() {
        val profile: ProfileResponse = Hawk.get(HAWK_USER_PROFILE)
        val lastName: String = if (profile.lastName != null) " ${profile.lastName}" else ""
        val fullName = profile.name + lastName
        intOut.setData(fullName, profile.email?: "", profile.photo)
    }

}