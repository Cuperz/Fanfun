package com.example.fanfun.ui.sketch

import com.example.fanfun.utils.User
import java.util.ArrayList

interface SketchContract {

    interface View{

    }

    interface Presenter{
        fun toVideoList()
        fun getVideoAmount(): Int
        fun getList(): ArrayList<User>
        fun toCamera()

    }

    interface Router{
        fun toVideoList()
        fun toCamera()

    }

    interface Interactor{
        fun getVideoAmount(): Int
        fun getList(): ArrayList<User>

    }

    interface InteractorOutput{

    }
}