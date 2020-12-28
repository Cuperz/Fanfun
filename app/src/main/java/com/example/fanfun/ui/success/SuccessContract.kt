package com.example.fanfun.ui.success

import com.example.fanfun.model.Request

interface SuccessContract  {

    interface View{

    }

    interface Presenter{
        fun toHome()
        fun deleteVideos(request: Request, videoFile: String)

    }

    interface Router{
        fun toHome()

    }

    interface Interactor{
        fun deleteVideos(request: Request, videoFile: String)

    }

    interface InteractorOutput{

    }
}