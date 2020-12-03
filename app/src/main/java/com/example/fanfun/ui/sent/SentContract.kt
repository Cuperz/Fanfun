package com.example.fanfun.ui.sent

import com.example.fanfun.model.Request
import java.util.ArrayList

interface SentContract {

    interface View{

    }

    interface Presenter{
        fun playVideo()
        fun getSentList(): ArrayList<Request>

    }

    interface Router{
        fun playVideo()

    }

    interface Interactor{
        fun getSentList(): ArrayList<Request>

    }

    interface InteractorOutput{

    }
}