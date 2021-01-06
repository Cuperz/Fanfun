package com.example.fanfun.ui.videoupload

import androidx.work.WorkInfo
import com.example.fanfun.model.Request
import com.google.common.util.concurrent.ListenableFuture

interface VideoUploadContract {

    interface View{

    }

    interface Presenter{
        fun videoUpload(request: Request, videoFile: String?)

        fun checkUpload(): WorkInfo.State
        fun videoSucceded(request: Request?, videoFile: String?)
        fun videoFailed(request: Request?, videoFile: String?)
    }

    interface Router{

        fun toSuccess(request: Request, videoFile: String)
        fun toError(request: Request, videoFile: String)
    }

    interface Interactor{
        fun uploadVideo(request: Request, videoFile: String?)

        fun checkUpload(): WorkInfo.State
        fun vidoSucceded(request: Request?, videoFile: String?)
        fun videoFailed(request: Request?, videoFile: String?)
    }

    interface InteractorOutput{
        fun toError(request: Request, videoFile: String)
        fun toSuccess(request: Request, videoFile: String?)

    }
}