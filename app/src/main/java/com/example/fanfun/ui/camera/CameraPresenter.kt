package com.example.fanfun.ui.camera

class CameraPresenter(activity: CameraActivity): CameraContract.Presenter, CameraContract.InteractorOutput {

    var mView: CameraContract.View = activity
    var mRouter: CameraContract.Router = CameraRouter(activity)
    var mInteractor: CameraContract.Interactor = CameraInteractor(this)


    override fun sendVideo(userId: String ,mVideoPath: String?) {
        mRouter.sendVideo(userId , mVideoPath)
    }

    override fun toHome() {
        mRouter.toHome()
    }

}