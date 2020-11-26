package com.example.fanfun.ui.camera

class CameraPresenter(activity: CameraActivity): CameraContract.Presenter, CameraContract.InteractorOutput {

    var mView: CameraContract.View = activity
    var mRouter: CameraContract.Router = CameraRouter(activity)
    var mInteractor: CameraContract.Interactor = CameraInteractor(this)


    override fun sendVideo(mVideoPath: String?) {
        mRouter.sendVideo(mVideoPath)
    }

    override fun toHome() {
        mRouter.toHome()
    }
}