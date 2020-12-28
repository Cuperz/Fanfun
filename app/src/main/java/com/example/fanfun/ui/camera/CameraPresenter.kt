package com.example.fanfun.ui.camera

import com.example.fanfun.model.Request

class CameraPresenter(activity: CameraActivity): CameraContract.Presenter, CameraContract.InteractorOutput {

    var mView: CameraContract.View = activity
    var mRouter: CameraContract.Router = CameraRouter(activity)
    var mInteractor: CameraContract.Interactor = CameraInteractor(this)


    override fun sendVideo(request: Request, mVideoPath: String?) {
        mRouter.sendVideo(request , mVideoPath)
    }

    override fun toHome() {
        mRouter.toHome()
    }

}