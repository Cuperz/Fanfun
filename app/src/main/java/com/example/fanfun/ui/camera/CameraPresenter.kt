package com.example.fanfun.ui.camera

class CameraPresenter(val activity: CameraActivity): CameraContract.Presenter, CameraContract.InteractorOutput {

    var mView: CameraContract.View = activity
    var mRouter: CameraContract.Router = CameraRouter(activity)
    var mInteractor: CameraContract.Interactor = CameraInteractor(this)

}