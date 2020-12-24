package com.example.fanfun.ui.sketch

import com.example.fanfun.model.Request
import com.example.fanfun.utils.User
import java.util.ArrayList

interface SketchContract {

    interface View{

    }

    interface Presenter{
        fun toVideoList(request: Request)
        fun getVideoAmount(requestId: String): Int
        fun getList(): ArrayList<User>
        fun toCamera(request: Request)

    }

    interface Router{
        fun toVideoList(request: Request)
        fun toCamera(request: Request)

    }

    interface Interactor{
        fun getVideoAmount(requestId: String): Int
        fun getList(): ArrayList<User>

    }

    interface InteractorOutput{

    }
}