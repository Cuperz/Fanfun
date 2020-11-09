package com.example.fanfun.ui.success

class SuccessPresenter(activity: SuccessActivity):SuccessContract.Presenter, SuccessContract.InteractorOutput {
    var mView: SuccessContract.View = activity
    var mRouter: SuccessContract.Router = SuccessRouter(activity)
    var mInteractor: SuccessContract.Interactor = SuccessInteractor(this)

    override fun toHome() {
        mRouter.toHome()
    }
}