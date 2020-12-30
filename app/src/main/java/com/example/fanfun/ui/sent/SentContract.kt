package com.example.fanfun.ui.sent

import com.example.fanfun.model.Request
import java.util.ArrayList

interface SentContract {

    interface View{
        fun listResult(sentList: ArrayList<Request>)

    }

    interface Presenter{
        fun playVideo(request: Request)
        fun getList()

    }

    interface Router{
        fun playVideo(request: Request)

    }

    interface Interactor{
        fun getList()

    }

    interface InteractorOutput{
        fun listResult(sentList: ArrayList<Request>)

    }
}