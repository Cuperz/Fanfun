package com.example.fanfun.ui.pending

import com.example.fanfun.model.Request
import java.util.ArrayList

interface PendingContract {

    interface View{

    }

    interface Presenter{
        fun toRecord(userId: String)
        fun getPendingList(): ArrayList<Request>

    }

    interface Router{
        fun toRecord(userId: String)

    }

    interface Interactor{
        fun getPendingList(): ArrayList<Request>

    }

    interface InteractorOutput{

    }
}