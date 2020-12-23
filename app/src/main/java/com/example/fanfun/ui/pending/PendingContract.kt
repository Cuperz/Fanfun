package com.example.fanfun.ui.pending

import com.example.fanfun.model.Request
import java.util.ArrayList

interface PendingContract {

    interface View{
        fun listResult(sentList: ArrayList<Request>)

    }

    interface Presenter{
        fun toRecord(userId: String)
        fun getList()

    }

    interface Router{
        fun toRecord(userId: String)

    }

    interface Interactor{
        fun getList()

    }

    interface InteractorOutput{
        fun listResult(sentList: ArrayList<Request>)

    }
}