package com.example.fanfun.ui.pending

import com.example.fanfun.model.Model
import java.util.ArrayList

interface PendingContract {

    interface View{

    }

    interface Presenter{
        fun toRecord()
        fun getPendingList(): ArrayList<Model.Request>

    }

    interface Router{
        fun toRecord()

    }

    interface Interactor{
        fun getPendingList(): ArrayList<Model.Request>

    }

    interface InteractorOutput{

    }
}