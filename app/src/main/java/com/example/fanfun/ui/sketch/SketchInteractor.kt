package com.example.fanfun.ui.sketch

import com.example.fanfun.utils.HAWK_USERS
import com.example.fanfun.utils.User
import com.example.fanfun.utils.getUserList
import com.example.fanfun.utils.getUserVideos
import com.orhanobut.hawk.Hawk
import java.util.ArrayList

class SketchInteractor(val intOut: SketchContract.InteractorOutput): SketchContract.Interactor {

    override fun getVideoAmount(): Int {
        return if (Hawk.contains(HAWK_USERS)){
            getUserVideos("1234")?.size!!
        }else{
            0
        }
    }

    override fun getList(): ArrayList<User> {
        return if (Hawk.contains(HAWK_USERS)){
            getUserList()
        }else{
            ArrayList()
        }
    }
}