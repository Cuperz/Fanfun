package com.example.fanfun.ui.profile

import com.example.fanfun.utils.deleteHawkData
import com.orhanobut.hawk.Hawk

class ProfileInteractor(var intOut: ProfileContract.InteractorOutput):ProfileContract.Interactor {

    override fun doLogOut() {
        //TODO informar al servidor que los videos seran movidos a pendientes
        deleteHawkData()
    }

}