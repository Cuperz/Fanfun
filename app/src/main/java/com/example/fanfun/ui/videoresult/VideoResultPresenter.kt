package com.example.fanfun.ui.videoresult

import com.example.fanfun.model.Request
import com.example.fanfun.utils.FROM_CAMERA
import com.example.fanfun.utils.FROM_SKETCH

class VideoResultPresenter(activity: VideoResultActivity): VideoResultContract.Presenter, VideoResultContract.InteractorOutput {

    var mView: VideoResultContract.View = activity
    var mRouter: VideoResultContract.Router = VideoResultRouter(activity)
    var mInteractor: VideoResultContract.Interactor = VideoResultInteractor(this, activity)

    override fun toCamera(request: Request) {
        mRouter.toCamera(request)
    }

    override fun sendVideo(request: Request,videoFile: String) {
        mRouter.toUpload(request,videoFile)

    }

    override fun toHome() {
        mRouter.toHome()
    }

    override fun deleteVideo(request: Request, videoFrom: Int, videoFile: String?) {
        mInteractor.deleteVideo(request,videoFrom,videoFile)
    }

    override fun videoDeleted(videoFrom: Int, request: Request) {
        when(videoFrom) {
            FROM_CAMERA -> mRouter.toCamera(request)
            FROM_SKETCH -> mRouter.toVideoList(request)
        }
    }

}