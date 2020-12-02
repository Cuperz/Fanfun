package com.example.fanfun.ui.sketch

import com.example.fanfun.utils.User
import java.util.ArrayList

interface SketchContract {

    interface View{

    }

    interface Presenter{
        fun toVideoList(userId: String)
        fun getVideoAmount(userId: String): Int
        fun getList(): ArrayList<User>
        fun toCamera(userId: String)

    }

    interface Router{
        fun toVideoList(userId: String)
        fun toCamera(userId: String)

    }

    interface Interactor{
        fun getVideoAmount(userId: String): Int
        fun getList(): ArrayList<User>

    }

    interface InteractorOutput{

    }
}