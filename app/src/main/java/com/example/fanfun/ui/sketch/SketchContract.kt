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

    }

    interface Router{
        fun toVideoList()

    }

    interface Interactor{
        fun getVideoAmount(): Int
        fun getList(): ArrayList<User>

    }

    interface InteractorOutput{

    }
}