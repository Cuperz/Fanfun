package com.example.fanfun.ui.success

import com.example.fanfun.model.Request

interface SuccessContract  {

    interface View{

    }

    interface Presenter{
        fun toHome()
    }

    interface Router{
        fun toHome()

    }

    interface Interactor{

    }

    interface InteractorOutput{

    }
}